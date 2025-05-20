package com.ldb.truck.Repository;

import com.ldb.truck.Entity.Supplier.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface SupplierEntityRepository extends JpaRepository<SupplierEntity, Long> {

    @Transactional
    @Query(value = "SELECT * FROM supplier WHERE supplierid = :supplierid ORDER BY supplierid DESC", nativeQuery = true)
    List<SupplierEntity> getSupplierBySupplierId(@Param("supplierid") int supplierId);

    @Transactional
    @Query(value = "SELECT * FROM supplier ORDER BY supplierid DESC", nativeQuery = true)
    List<SupplierEntity> getSupplierBySupplierAll();


    @Transactional
    @Modifying
    @Query(value = "UPDATE supplier SET supplier_name = :supplierName, mobile = :mobile, location = :location, email = :email, supplier_type = :supplier_type WHERE supplierid = :supplierid", nativeQuery = true)
    int updateSupplier(
            @Param("supplierName") String supplierName,
            @Param("mobile") String mobile,
            @Param("location") String location,
            @Param("email") String email,
            @Param("supplier_type") String supplierType,
            @Param("supplierid") int supplierId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM supplier WHERE supplierid = :supplierid", nativeQuery = true)
    int delSupplier(@Param("supplierid") int supplierId);
}