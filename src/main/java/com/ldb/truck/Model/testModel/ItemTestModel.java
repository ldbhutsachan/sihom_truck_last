package com.ldb.truck.Model.testModel;

public class ItemTestModel {

    private String branchName;
    private String houseId;
    private String khname;
    private String borName;
    private Double totalLAK;
    private Double totalTHB;
    private Double totalUSD;

    // --- Constructor ---
    public ItemTestModel() {}

    public ItemTestModel(String branchName, String houseId, String khname, String borName,
                         Double totalLAK, Double totalTHB, Double totalUSD) {
        this.branchName = branchName;
        this.houseId = houseId;
        this.khname = khname;
        this.borName = borName;
        this.totalLAK = totalLAK;
        this.totalTHB = totalTHB;
        this.totalUSD = totalUSD;
    }

    // --- Getter & Setter ---
    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getKhname() {
        return khname;
    }

    public void setKhname(String khname) {
        this.khname = khname;
    }

    public String getBorName() {
        return borName;
    }

    public void setBorName(String borName) {
        this.borName = borName;
    }

    public Double getTotalLAK() {
        return totalLAK;
    }

    public void setTotalLAK(Double totalLAK) {
        this.totalLAK = totalLAK;
    }

    public Double getTotalTHB() {
        return totalTHB;
    }

    public void setTotalTHB(Double totalTHB) {
        this.totalTHB = totalTHB;
    }

    public Double getTotalUSD() {
        return totalUSD;
    }

    public void setTotalUSD(Double totalUSD) {
        this.totalUSD = totalUSD;
    }
}
