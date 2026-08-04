package com.ldb.truck.Service.RegisterService;

import com.ldb.truck.Entity.Staff.StaffEntity;
import com.ldb.truck.Model.DataResponse;
import com.ldb.truck.Model.StaffStatement.StaffStatementReq;
import com.ldb.truck.Model.StaffStatement.StaffStatementDetailRes;
import com.ldb.truck.Repository.Staffs.UserRepository;
import com.ldb.truck.Repository.Staffs.StaffStatementRepository;
import com.ldb.truck.Dao.StaffStatementDao;
import com.ldb.truck.Entity.Staff.StaffStatement;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class ExcelImportService {

    private final UserRepository userRepository;
    private final StaffStatementRepository staffStatementRepository;
    private final StaffStatementDao staffStatementDao;

    // เพิ่ม @Transactional
    // ถ้า error กลางคัน → rollback ทั้งหมด
    @Transactional
    public DataResponse importStaffFromExcel(String token, MultipartFile file) {

        DataResponse response = new DataResponse();

        try {
            // Step 1: เช็ค token
            StaffEntity requester = userRepository.findByToken(token)
                    .orElseThrow(() -> new RuntimeException("Token ไม่ถูกต้อง"));

            if (requester.getTokenExpiredAt() != null
                    && requester.getTokenExpiredAt().isBefore(LocalDateTime.now())) {
                throw new RuntimeException("Token not found Please Login again");
            }

            // Step 2: เช็ค HR/ADMIN
            if (!requester.getRole().equals("ADMIN")
                    && !requester.getRole().equals("HR")) {
                throw new RuntimeException("ບໍ່ມີສິດ ສະເພາະ HR ຫຼື ADMIN ເທົ່ານັ້ນ");
            }

            // Step 3: เช็คไฟล์
            String filename = file.getOriginalFilename();
            if (filename == null
                    || (!filename.endsWith(".xlsx")
                            && !filename.endsWith(".xls"))) {
                throw new RuntimeException(
                        "ກະລຸນາອັບໂຫລດໄຟລ໌ Excel (.xlsx, .xls) ເທົ່ານັ້ນ");
            }

            // เช็คขนาดไฟล์
            if (file.getSize() > 10 * 1024 * 1024) {
                throw new RuntimeException("ໄຟລ໌ໃຫຍ່ເກີນໄປ ສູງສຸດ 10MB");
            }

            // Step 4: อ่าน Excel
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            List<Map<String, Object>> updatedList = new ArrayList<>();
            List<Map<String, Object>> skippedList = new ArrayList<>();
            List<Map<String, Object>> errorList = new ArrayList<>();
            List<StaffEntity> toUpdate = new ArrayList<>();

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null)
                    continue;

                try {
                    String staffCode = getCellValue(row, 0);
                    if (staffCode == null || staffCode.isEmpty())
                        continue;

                    Optional<StaffEntity> optStaff = userRepository.findByStaffCode(staffCode);

                    if (optStaff.isEmpty()) {
                        Map<String, Object> skip = new LinkedHashMap<>();
                        skip.put("row", i + 1);
                        skip.put("staffCode", staffCode);
                        skip.put("reason", "ບໍ່ພົບ Staff Code: " + staffCode);
                        skippedList.add(skip);
                        continue;
                    }

                    StaffEntity staff = optStaff.get();

                    setIfNotEmpty(staff::setUsername, getCellValue(row, 1));
                    setIfNotEmpty(staff::setLao_name, getCellValue(row, 2));
                    setIfNotEmpty(staff::setPhone, getCellValue(row, 3));
                    setIfNotEmpty(staff::setGender, getCellValue(row, 4));

                    String birthDate = getCellValue(row, 5);
                    if (birthDate != null && !birthDate.isEmpty())
                        staff.setBirth_date(LocalDate.parse(birthDate, dateFormatter));

                    setIfNotEmpty(staff::setAddress, getCellValue(row, 6));
                    setIfNotEmpty(staff::setStatus, getCellValue(row, 7));

                    String borId = getCellValue(row, 8);
                    if (borId != null && !borId.isEmpty())
                        staff.setBorId(Integer.parseInt(borId));

                    String deptId = getCellValue(row, 9);
                    if (deptId != null && !deptId.isEmpty())
                        staff.setDept_id(Long.parseLong(deptId));

                    String posId = getCellValue(row, 10);
                    if (posId != null && !posId.isEmpty())
                        staff.setPos_id(Long.parseLong(posId));

                    String salary = getCellValue(row, 11);
                    if (salary != null && !salary.isEmpty())
                        staff.setBaseSalary(new BigDecimal(salary));

                    String quotaSick = getCellValue(row, 12);
                    if (quotaSick != null && !quotaSick.isEmpty())
                        staff.setLeaveQuotaSick(Integer.parseInt(quotaSick));

                    String quotaAnnual = getCellValue(row, 13);
                    if (quotaAnnual != null && !quotaAnnual.isEmpty())
                        staff.setLeaveQuotaAnnual(Integer.parseInt(quotaAnnual));

                    String quotaCasual = getCellValue(row, 14);
                    if (quotaCasual != null && !quotaCasual.isEmpty())
                        staff.setLeaveQuotaCasual(Integer.parseInt(quotaCasual));

                    String quotaAccident = getCellValue(row, 15);
                    if (quotaAccident != null && !quotaAccident.isEmpty())
                        staff.setLeaveQuotaAccident(Integer.parseInt(quotaAccident));

                    String startWorkDate = getCellValue(row, 16);
                    if (startWorkDate != null && !startWorkDate.isEmpty())
                        staff.setStartwork_date(
                                LocalDate.parse(startWorkDate, dateFormatter));

                    String workSchedule = getCellValue(row, 17);
                    if (workSchedule != null && !workSchedule.isEmpty()) {
                        staff.setWorkSchedule(workSchedule);
                        if (workSchedule.equals("CYCLE")) {
                            String cwd = getCellValue(row, 18);
                            String cod = getCellValue(row, 19);
                            String csd = getCellValue(row, 20);
                            if (cwd != null && !cwd.isEmpty())
                                staff.setCycleWorkDays(Integer.parseInt(cwd));
                            if (cod != null && !cod.isEmpty())
                                staff.setCycleOffDays(Integer.parseInt(cod));
                            if (csd != null && !csd.isEmpty())
                                staff.setCycleStartDate(
                                        LocalDate.parse(csd, dateFormatter));
                        } else {
                            staff.setCycleWorkDays(null);
                            staff.setCycleOffDays(null);
                            staff.setCycleStartDate(null);
                        }
                    }

                    // เพิ่มการอัปเดต work_place จากคอลัมน์ 21 (V)
                    setIfNotEmpty(staff::setWork_place, getCellValue(row, 21));

                    toUpdate.add(staff); // เก็บไว้ก่อน

                    Map<String, Object> updated = new LinkedHashMap<>();
                    updated.put("row", i + 1);
                    updated.put("staffCode", staffCode);
                    updated.put("username", staff.getUsername());
                    updatedList.add(updated);

                } catch (Exception rowError) {
                    Map<String, Object> error = new LinkedHashMap<>();
                    error.put("row", i + 1);
                    error.put("reason", rowError.getMessage());
                    errorList.add(error);
                }
            }

            workbook.close();

            // Save ทีเดียว
            userRepository.saveAll(toUpdate);

            Map<String, Object> data = new LinkedHashMap<>();
            data.put("totalUpdated", updatedList.size());
            data.put("totalSkipped", skippedList.size());
            data.put("totalError", errorList.size());
            data.put("updated", updatedList);
            data.put("skipped", skippedList);
            data.put("errors", errorList);

            response.setStatus("00");
            response.setMessage("ນຳເຂົ້າຂໍ້ມູນສຳເລັດ");
            response.setDataResponse(data);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("01");
            response.setMessage(e.getMessage());
            response.setDataResponse(null);
        }

        return response;
    }

    // Helper — ดึงค่าจาก cell
    private String getCellValue(Row row, int colIndex) {
        Cell cell = row.getCell(colIndex);
        if (cell == null)
            return null;

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getLocalDateTimeCellValue()
                            .toLocalDate().toString();
                }
                double val = cell.getNumericCellValue();
                if (val == Math.floor(val)) {
                    return String.valueOf((long) val);
                }
                // Avoid scientific notation like 3.262518E8
                return new java.text.DecimalFormat("#.##").format(val);
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                switch (cell.getCachedFormulaResultType()) {
                    case STRING:
                        return cell.getStringCellValue().trim();
                    case NUMERIC:
                        double fVal = cell.getNumericCellValue();
                        if (fVal == Math.floor(fVal)) {
                            return String.valueOf((long) fVal);
                        }
                        // Use formatting to avoid scientific notation for large numbers
                        return new java.text.DecimalFormat("#.##").format(fVal);
                    case BOOLEAN:
                        return String.valueOf(cell.getBooleanCellValue());
                    default:
                        return null;
                }
            case BLANK:
                return null;
            default:
                return null;
        }
    }

    // ✅ Helper — set ถ้าไม่ว่าง
    private void setIfNotEmpty(Consumer<String> setter, String value) {
        if (value != null && !value.isEmpty()) {
            setter.accept(value);
        }
    }

    @Transactional
    public DataResponse uploadStatement(String token, String title, String statementDate, MultipartFile file) {
        DataResponse response = new DataResponse();
        try {
            // Step 1: เช็ค token
            StaffEntity requester = userRepository.findByToken(token)
                    .orElseThrow(() -> new RuntimeException("Token not found"));
            // Step 2: เช็ค HR/ADMIN
            if (!requester.getRole().equals("ADMIN")
                    && !requester.getRole().equals("HR")) {
                throw new RuntimeException("ທ່ານບໍ່ມີສິດ, ສະເພາະ HR ຫຼື ADMIN ເທົ່ານັ້ນ");
            }

            // Step 3: เช็คไฟล์
            String filename = file.getOriginalFilename();
            if (filename == null
                    || (!filename.endsWith(".xlsx") && !filename.endsWith(".xls") && !filename.endsWith(".csv"))) {
                throw new RuntimeException("ກະລຸນາອັບໂຫຼດໄຟລ໌ Excel (.xlsx, .xls) ຫຼື CSV (.csv)");
            }

            List<StaffStatement> statementList = new ArrayList<>();
            LocalDateTime now = LocalDateTime.now();
            String saveBy = requester.getUsername();

            if (filename.endsWith(".csv")) {
                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
                    String line;
                    int rowNumber = 0;
                    while ((line = br.readLine()) != null) {
                        if (rowNumber == 0) {
                            rowNumber++;
                            continue; // skip header
                        }

                        String[] cols = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                        boolean isEmptyRow = true;
                        for (String col : cols) {
                            if (col != null && !col.trim().isEmpty()) {
                                isEmptyRow = false;
                                break;
                            }
                        }
                        if (isEmptyRow)
                            continue;

                        StaffStatement statement = new StaffStatement();
                        statement.setStaffCode(getCSVValue(cols, 0));
                        statement.setUsdSalary(getCSVValue(cols, 1));
                        statement.setLak_salary(getCSVValue(cols, 2));
                        statement.setWorkDay(getCSVValue(cols, 3));
                        statement.setAmount_salary(getCSVValue(cols, 4));
                        statement.setOt(getCSVValue(cols, 5));
                        statement.setTransportationAllowance(getCSVValue(cols, 6));
                        statement.setAmountMoney(getCSVValue(cols, 7));
                        statement.setBond(getCSVValue(cols, 8));
                        statement.setHealthInsuranceDeduction(getCSVValue(cols, 9));
                        statement.setIncomeTax(getCSVValue(cols, 10));
                        statement.setTotalDeductions(getCSVValue(cols, 11));
                        statement.setTotalEarningsLak(getCSVValue(cols, 12));
                        statement.setCommentIncome(getCSVValue(cols, 13));
                        statement.setCommentOutcome(getCSVValue(cols, 14));

                        statement.setTitle(title);
                        statement.setStatementDate(statementDate);
                        statement.setSaveBy(saveBy);
                        statement.setCreateDate(now);

                        statementList.add(statement);
                        rowNumber++;
                    }
                }
            } else {
                try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
                    Sheet sheet = workbook.getSheetAt(0);
                    Iterator<Row> rows = sheet.iterator();

                    int rowNumber = 0;
                    while (rows.hasNext()) {
                        Row currentRow = rows.next();
                        // skip header
                        if (rowNumber == 0) {
                            rowNumber++;
                            continue;
                        }

                        // Check if row is completely empty
                        boolean isEmptyRow = true;
                        for (int i = 0; i <= 14; i++) {
                            if (getCellValue(currentRow, i) != null && !getCellValue(currentRow, i).trim().isEmpty()) {
                                isEmptyRow = false;
                                break;
                            }
                        }
                        if (isEmptyRow) {
                            continue;
                        }

                        StaffStatement statement = new StaffStatement();
                        statement.setStaffCode(getCellValue(currentRow, 0));
                        statement.setUsdSalary(getCellValue(currentRow, 1));
                        statement.setLak_salary(getCellValue(currentRow, 2));
                        statement.setWorkDay(getCellValue(currentRow, 3));
                        statement.setAmount_salary(getCellValue(currentRow, 4));
                        statement.setOt(getCellValue(currentRow, 5));
                        statement.setTransportationAllowance(getCellValue(currentRow, 6));
                        statement.setAmountMoney(getCellValue(currentRow, 7));
                        statement.setBond(getCellValue(currentRow, 8));
                        statement.setHealthInsuranceDeduction(getCellValue(currentRow, 9));
                        statement.setIncomeTax(getCellValue(currentRow, 10));
                        statement.setTotalDeductions(getCellValue(currentRow, 11));
                        statement.setTotalEarningsLak(getCellValue(currentRow, 12));
                        statement.setCommentIncome(getCellValue(currentRow, 13));
                        statement.setCommentOutcome(getCellValue(currentRow, 14));

                        statement.setTitle(title);
                        statement.setStatementDate(statementDate);
                        statement.setSaveBy(saveBy);
                        statement.setCreateDate(now);

                        statementList.add(statement);
                        rowNumber++;
                    }
                }
            }

            staffStatementRepository.saveAll(statementList);

            response.setStatus("00");
            response.setMessage("ອັບໂຫຼດ Statement ສຳເລັດ " + statementList.size() + " ລາຍການ");
            response.setDataResponse(null);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("01");
            response.setMessage("ເກີດຂໍ້ຜິດພາດ: " + e.getMessage());
            response.setDataResponse(null);
            throw new RuntimeException(e); // for rollback
        }

        return response;
    }

    private String getCSVValue(String[] cols, int index) {
        if (index >= cols.length)
            return null;
        String val = cols[index];
        if (val == null)
            return null;
        val = val.trim();
        if (val.startsWith("\"") && val.endsWith("\"") && val.length() >= 2) {
            val = val.substring(1, val.length() - 1);
        }
        return val;
    }

    public DataResponse listStaffStatementData(String token, StaffStatementReq req) {
        DataResponse response = new DataResponse();
        try {
            // Step 1: Check token
            String reqToken = (token != null && !token.isEmpty()) ? token : req.getToken();

            StaffEntity requester = userRepository.findByToken(reqToken)
                    .orElseThrow(() -> new RuntimeException("Token not found"));

            List<StaffStatementDetailRes> list;
            if ("ADMIN".equals(requester.getRole()) || "HR".equals(requester.getRole())) {
                // ADMIN/HR can search any staff or apply filters
                list = staffStatementDao.searchStaffStatementData(
                        req.getStaffCode(),
                        req.getBorId(),
                        req.getDeptId(),
                        req.getStartDate(),
                        req.getEndDate());
            } else {
                // Regular staff can only see their own statements
                list = staffStatementDao.searchStaffStatementData(
                        requester.getStaffCode(),
                        null,
                        null,
                        req.getStartDate(),
                        req.getEndDate());
            }

            response.setStatus("00");
            response.setMessage("ດຶงຂໍ້ມູນສຳເລັດ");
            response.setDataResponse(list);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("01");
            response.setMessage("ເກີດຂໍ້ຜິດພາດ: " + e.getMessage());
            response.setDataResponse(null);
        }
        return response;
    }

    public DataResponse updateStaffStatement(String token,
            com.ldb.truck.Model.StaffStatement.StaffStatementUpdateReq req) {
        DataResponse response = new DataResponse();
        try {
            StaffEntity requester = userRepository.findByToken(token)
                    .orElseThrow(() -> new RuntimeException("Token ไม่ถูกต้อง"));

            if (!"ADMIN".equals(requester.getRole()) && !"HR".equals(requester.getRole())) {
                throw new RuntimeException("ທ່ານບໍ່ມີສິດ, ສະເພາະ HR ຫຼື ADMIN ເທົ່ານັ້ນ");
            }

            if (req.getId() == null) {
                throw new RuntimeException("ID ບໍ່ສາມາດເປັນຄ່າວ່າງໄດ້");
            }

            StaffStatement statement = staffStatementRepository.findById(req.getId())
                    .orElseThrow(() -> new RuntimeException("ບໍ່ພົບຂໍ້ມູນທີ່ຕ້ອງການແກ້ໄຂ"));

            if (req.getTitle() != null)
                statement.setTitle(req.getTitle());
            if (req.getStatementDate() != null)
                statement.setStatementDate(req.getStatementDate());
            if (req.getUsdSalary() != null)
                statement.setUsdSalary(req.getUsdSalary());
            if (req.getLak_salary() != null)
                statement.setLak_salary(req.getLak_salary());
            if (req.getWorkDay() != null)
                statement.setWorkDay(req.getWorkDay());
            if (req.getAmount_salary() != null)
                statement.setAmount_salary(req.getAmount_salary());
            if (req.getOt() != null)
                statement.setOt(req.getOt());
            if (req.getTransportationAllowance() != null)
                statement.setTransportationAllowance(req.getTransportationAllowance());
            if (req.getAmountMoney() != null)
                statement.setAmountMoney(req.getAmountMoney());
            if (req.getBond() != null)
                statement.setBond(req.getBond());
            if (req.getHealthInsuranceDeduction() != null)
                statement.setHealthInsuranceDeduction(req.getHealthInsuranceDeduction());
            if (req.getIncomeTax() != null)
                statement.setIncomeTax(req.getIncomeTax());
            if (req.getTotalDeductions() != null)
                statement.setTotalDeductions(req.getTotalDeductions());
            if (req.getTotalEarningsLak() != null)
                statement.setTotalEarningsLak(req.getTotalEarningsLak());
            if (req.getCommentIncome() != null)
                statement.setCommentIncome(req.getCommentIncome());
            if (req.getCommentOutcome() != null)
                statement.setCommentOutcome(req.getCommentOutcome());

            statement.setSaveBy(requester.getUsername());
            statement.setCreateDate(LocalDateTime.now());

            staffStatementRepository.save(statement);

            response.setStatus("00");
            response.setMessage("ແກ້ໄຂຂໍ້ມູນສຳເລັດ");
            response.setDataResponse(statement);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("01");
            response.setMessage("ເກີດຂໍ້ຜິດພາດ: " + e.getMessage());
            response.setDataResponse(null);
        }
        return response;
    }
}
