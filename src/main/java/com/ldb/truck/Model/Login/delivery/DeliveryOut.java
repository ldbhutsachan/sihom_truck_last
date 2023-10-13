package com.ldb.truck.Model.Login.delivery;

public class DeliveryOut {
    private String id;
    private String IdDelivery ;
    private String dateTime;
    private String truckId;
    private String platNo;
    private String staftId;
    private String staftName;
    private String staftMobile;
    private String customerId;
    private String customerName;
    private String addressProvinceStart;
    private String adddressProvinceEnd;
    private String addressStartDate;
    private String addressEndDate;
    private String addressStartDetail;
    private String addressEndDetail;
    private String distance;
    private String productId;
    private String productName;
    private String payToatalAmount;
    private String weight;
    private String payAmount;
    private String totalGlass;
    private String priceGlass;

    public DeliveryOut() {
    }

    public DeliveryOut(String id, String idDelivery, String dateTime, String truckId, String platNo, String staftId, String staftName, String staftMobile, String customerId, String customerName, String addressProvinceStart, String adddressProvinceEnd, String addressStartDate, String addressEndDate, String addressStartDetail, String addressEndDetail, String distance, String productId, String productName, String payToatalAmount, String weight, String payAmount, String totalGlass, String priceGlass) {
        this.id = id;
        IdDelivery = idDelivery;
        this.dateTime = dateTime;
        this.truckId = truckId;
        this.platNo = platNo;
        this.staftId = staftId;
        this.staftName = staftName;
        this.staftMobile = staftMobile;
        this.customerId = customerId;
        this.customerName = customerName;
        this.addressProvinceStart = addressProvinceStart;
        this.adddressProvinceEnd = adddressProvinceEnd;
        this.addressStartDate = addressStartDate;
        this.addressEndDate = addressEndDate;
        this.addressStartDetail = addressStartDetail;
        this.addressEndDetail = addressEndDetail;
        this.distance = distance;
        this.productId = productId;
        this.productName = productName;
        this.payToatalAmount = payToatalAmount;
        this.weight = weight;
        this.payAmount = payAmount;
        this.totalGlass = totalGlass;
        this.priceGlass = priceGlass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdDelivery() {
        return IdDelivery;
    }

    public void setIdDelivery(String idDelivery) {
        IdDelivery = idDelivery;
    }

    public String getTruckId() {
        return truckId;
    }

    public void setTruckId(String truckId) {
        this.truckId = truckId;
    }

    public String getPlatNo() {
        return platNo;
    }

    public void setPlatNo(String platNo) {
        this.platNo = platNo;
    }

    public String getStaftId() {
        return staftId;
    }

    public void setStaftId(String staftId) {
        this.staftId = staftId;
    }

    public String getStaftName() {
        return staftName;
    }

    public void setStaftName(String staftName) {
        this.staftName = staftName;
    }

    public String getStaftMobile() {
        return staftMobile;
    }

    public void setStaftMobile(String staftMobile) {
        this.staftMobile = staftMobile;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddressProvinceStart() {
        return addressProvinceStart;
    }

    public void setAddressProvinceStart(String addressProvinceStart) {
        this.addressProvinceStart = addressProvinceStart;
    }

    public String getAdddressProvinceEnd() {
        return adddressProvinceEnd;
    }

    public void setAdddressProvinceEnd(String adddressProvinceEnd) {
        this.adddressProvinceEnd = adddressProvinceEnd;
    }

    public String getAddressStartDate() {
        return addressStartDate;
    }

    public void setAddressStartDate(String addressStartDate) {
        this.addressStartDate = addressStartDate;
    }

    public String getAddressEndDate() {
        return addressEndDate;
    }

    public void setAddressEndDate(String addressEndDate) {
        this.addressEndDate = addressEndDate;
    }

    public String getAddressStartDetail() {
        return addressStartDetail;
    }

    public void setAddressStartDetail(String addressStartDetail) {
        this.addressStartDetail = addressStartDetail;
    }

    public String getAddressEndDetail() {
        return addressEndDetail;
    }

    public void setAddressEndDetail(String addressEndDetail) {
        this.addressEndDetail = addressEndDetail;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPayToatalAmount() {
        return payToatalAmount;
    }

    public void setPayToatalAmount(String payToatalAmount) {
        this.payToatalAmount = payToatalAmount;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getTotalGlass() {
        return totalGlass;
    }

    public void setTotalGlass(String totalGlass) {
        this.totalGlass = totalGlass;
    }

    public String getPriceGlass() {
        return priceGlass;
    }

    public void setPriceGlass(String priceGlass) {
        this.priceGlass = priceGlass;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "DeliveryOut{" +
                "id='" + id + '\'' +
                ", IdDelivery='" + IdDelivery + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", truckId='" + truckId + '\'' +
                ", platNo='" + platNo + '\'' +
                ", staftId='" + staftId + '\'' +
                ", staftName='" + staftName + '\'' +
                ", staftMobile='" + staftMobile + '\'' +
                ", customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", addressProvinceStart='" + addressProvinceStart + '\'' +
                ", adddressProvinceEnd='" + adddressProvinceEnd + '\'' +
                ", addressStartDate='" + addressStartDate + '\'' +
                ", addressEndDate='" + addressEndDate + '\'' +
                ", addressStartDetail='" + addressStartDetail + '\'' +
                ", addressEndDetail='" + addressEndDetail + '\'' +
                ", distance='" + distance + '\'' +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", payToatalAmount='" + payToatalAmount + '\'' +
                ", weight='" + weight + '\'' +
                ", payAmount='" + payAmount + '\'' +
                ", totalGlass='" + totalGlass + '\'' +
                ", priceGlass='" + priceGlass + '\'' +
                '}';
    }
}
