package com.zhongcaidita.test.pojo;
/*@authour guoliangliang
 *
 *@date 2018/5/4 17:14
 */

import java.io.Serializable;

public class Car implements Serializable {
    private Integer carId;

    private String plateNumber; // 车牌号码

    private Double latitude; // 定位纬度

    private Double longitude; // 定位经度

    private String dataTimeGps; // GPS时间

    private Double speedGps; // GPS速度 km/h

    private Double mileage; // 里程 km

    private Double altitude; // 海拔m

    private Double northAngularSeparation; // 正北方向夹角

    private String statusBit; // 位置状态位

    private String addCarSignalStatusBit; // 扩展车辆信号状态位

    private String province;// 省份

    private Integer count_sum;

    private Integer start;

    private Integer rows;

    private String fromData;

    private String fromEnd;

    private String flagTime;

    private String twoTime;

    private String fourTime;

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getDataTimeGps() {
        return dataTimeGps;
    }

    public void setDataTimeGps(String dataTimeGps) {
        this.dataTimeGps = dataTimeGps;
    }

    public Double getSpeedGps() {
        return speedGps;
    }

    public void setSpeedGps(Double speedGps) {
        this.speedGps = speedGps;
    }

    public Double getMileage() {
        return mileage;
    }

    public void setMileage(Double mileage) {
        this.mileage = mileage;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public Double getNorthAngularSeparation() {
        return northAngularSeparation;
    }

    public void setNorthAngularSeparation(Double northAngularSeparation) {
        this.northAngularSeparation = northAngularSeparation;
    }

    public String getStatusBit() {
        return statusBit;
    }

    public void setStatusBit(String statusBit) {
        this.statusBit = statusBit;
    }

    public String getAddCarSignalStatusBit() {
        return addCarSignalStatusBit;
    }

    public void setAddCarSignalStatusBit(String addCarSignalStatusBit) {
        this.addCarSignalStatusBit = addCarSignalStatusBit;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Integer getCount_sum() {
        return count_sum;
    }

    public void setCount_sum(Integer count_sum) {
        this.count_sum = count_sum;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getFromData() {
        return fromData;
    }

    public String getFromEnd() {
        return fromEnd;
    }

    public void setFromEnd(String fromEnd) {
        this.fromEnd = fromEnd;
    }

    public void setFromData(String fromData) {
        this.fromData = fromData;
    }

    public String getFlagTime() {
        return flagTime;
    }

    public void setFlagTime(String flagTime) {
        this.flagTime = flagTime;
    }

    public String getTwoTime() {
        return twoTime;
    }

    public void setTwoTime(String twoTime) {
        this.twoTime = twoTime;
    }

    public String getFourTime() {
        return fourTime;
    }

    public void setFourTime(String fourTime) {
        this.fourTime = fourTime;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", plateNumber='" + plateNumber + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", dataTimeGps='" + dataTimeGps + '\'' +
                ", speedGps=" + speedGps +
                ", mileage=" + mileage +
                ", altitude=" + altitude +
                ", northAngularSeparation=" + northAngularSeparation +
                ", statusBit='" + statusBit + '\'' +
                ", addCarSignalStatusBit='" + addCarSignalStatusBit + '\'' +
                ", province='" + province + '\'' +
                ", count_sum=" + count_sum +
                ", start=" + start +
                ", rows=" + rows +
                ", fromData='" + fromData + '\'' +
                ", fromEnd='" + fromEnd + '\'' +
                ", flagTime='" + flagTime + '\'' +
                ", twoTime='" + twoTime + '\'' +
                ", fourTime='" + fourTime + '\'' +
                '}';
    }

    public Car() {}

    public Car(Integer carId, String plateNumber, Double latitude, Double longitude, String dataTimeGps, Double speedGps, Double mileage, Double altitude, Double northAngularSeparation, String statusBit, String addCarSignalStatusBit, String province, Integer count_sum, Integer start, Integer rows, String fromData, String fromEnd, String flagTime, String twoTime, String fourTime) {
        this.carId = carId;
        this.plateNumber = plateNumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dataTimeGps = dataTimeGps;
        this.speedGps = speedGps;
        this.mileage = mileage;
        this.altitude = altitude;
        this.northAngularSeparation = northAngularSeparation;
        this.statusBit = statusBit;
        this.addCarSignalStatusBit = addCarSignalStatusBit;
        this.province = province;
        this.count_sum = count_sum;
        this.start = start;
        this.rows = rows;
        this.fromData = fromData;
        this.fromEnd = fromEnd;
        this.flagTime = flagTime;
        this.twoTime = twoTime;
        this.fourTime = fourTime;
    }
}
