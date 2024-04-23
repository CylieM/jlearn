package com.example.jlearnn;


public class DataClass {
    private String dataRomaji;
    private String dataDesc;
    private String dataExample;
    private String dataImage;
    private String key;
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getDataRomaji() {
        return dataRomaji;
    }
    public String getDataDesc() {
        return dataDesc;
    }
    public String getDataExample() {
        return dataExample;
    }
    public String getDataImage() {
        return dataImage;
    }
    public DataClass(String dataRomaji, String dataDesc, String dataExample, String dataImage) {
        this.dataRomaji = dataRomaji;
        this.dataDesc = dataDesc;
        this.dataExample = dataExample;
        this.dataImage = dataImage;
    }
    public DataClass(){
    }
}