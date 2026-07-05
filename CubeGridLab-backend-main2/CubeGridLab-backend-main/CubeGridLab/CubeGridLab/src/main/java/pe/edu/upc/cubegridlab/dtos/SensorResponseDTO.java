package pe.edu.upc.cubegridlab.dtos;

/**
 * DTO de respuesta para Sensor
 * Incluye información básica del sensor y dispositivo asociado
 * Sin datos sensibles o anidados complejos
 */
public class SensorResponseDTO {
    private int idSensor;
    private String name;
    private String type;
    private String unitMeasurement;
    private Double minValue;
    private Double maxValue;
    private String status;
    private String registrationDate;
    private IoTDeviceBasicDTO iotDevice;

    public SensorResponseDTO() {
    }

    public SensorResponseDTO(int idSensor, String name, String type, String unitMeasurement,
                           Double minValue, Double maxValue, String status, String registrationDate,
                           IoTDeviceBasicDTO iotDevice) {
        this.idSensor = idSensor;
        this.name = name;
        this.type = type;
        this.unitMeasurement = unitMeasurement;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.status = status;
        this.registrationDate = registrationDate;
        this.iotDevice = iotDevice;
    }

    // Getters and Setters
    public int getIdSensor() {
        return idSensor;
    }

    public void setIdSensor(int idSensor) {
        this.idSensor = idSensor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnitMeasurement() {
        return unitMeasurement;
    }

    public void setUnitMeasurement(String unitMeasurement) {
        this.unitMeasurement = unitMeasurement;
    }

    public Double getMinValue() {
        return minValue;
    }

    public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }

    public Double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public IoTDeviceBasicDTO getIotDevice() {
        return iotDevice;
    }

    public void setIotDevice(IoTDeviceBasicDTO iotDevice) {
        this.iotDevice = iotDevice;
    }

    /**
     * DTO básico para información del dispositivo IoT
     */
    public static class IoTDeviceBasicDTO {
        private int idDevice;
        private String name;
        private String type;

        public IoTDeviceBasicDTO() {
        }

        public IoTDeviceBasicDTO(int idDevice, String name, String type) {
            this.idDevice = idDevice;
            this.name = name;
            this.type = type;
        }

        public int getIdDevice() {
            return idDevice;
        }

        public void setIdDevice(int idDevice) {
            this.idDevice = idDevice;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}

