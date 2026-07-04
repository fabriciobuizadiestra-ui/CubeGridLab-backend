package pe.edu.upc.cubegridlab.dtos;

/**
 * DTO de respuesta para SensorData
 * Incluye información de la lectura y referencias básicas
 * Sin datos sensibles o anidados complejos
 */
public class SensorDataResponseDTO {
    private int idData;
    private Double value;
    private String timestamp;
    private String type;
    private String eventType;
    private String message;
    private SensorBasicDTO sensor;
    private SimulacionBasicDTO simulacion;

    public SensorDataResponseDTO() {
    }

    public SensorDataResponseDTO(int idData, Double value, String timestamp, String type,
                               String eventType, String message, SensorBasicDTO sensor,
                               SimulacionBasicDTO simulacion) {
        this.idData = idData;
        this.value = value;
        this.timestamp = timestamp;
        this.type = type;
        this.eventType = eventType;
        this.message = message;
        this.sensor = sensor;
        this.simulacion = simulacion;
    }

    // Getters and Setters
    public int getIdData() {
        return idData;
    }

    public void setIdData(int idData) {
        this.idData = idData;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SensorBasicDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorBasicDTO sensor) {
        this.sensor = sensor;
    }

    public SimulacionBasicDTO getSimulacion() {
        return simulacion;
    }

    public void setSimulacion(SimulacionBasicDTO simulacion) {
        this.simulacion = simulacion;
    }

    /**
     * DTO básico para información del sensor
     */
    public static class SensorBasicDTO {
        private int idSensor;
        private String name;
        private String type;
        private String unitMeasurement;

        public SensorBasicDTO() {
        }

        public SensorBasicDTO(int idSensor, String name, String type, String unitMeasurement) {
            this.idSensor = idSensor;
            this.name = name;
            this.type = type;
            this.unitMeasurement = unitMeasurement;
        }

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
    }

    /**
     * DTO básico para información de la simulación
     */
    public static class SimulacionBasicDTO {
        private int idSimulacion;
        private String nombre;
        private String estado;

        public SimulacionBasicDTO() {
        }

        public SimulacionBasicDTO(int idSimulacion, String nombre, String estado) {
            this.idSimulacion = idSimulacion;
            this.nombre = nombre;
            this.estado = estado;
        }

        public int getIdSimulacion() {
            return idSimulacion;
        }

        public void setIdSimulacion(int idSimulacion) {
            this.idSimulacion = idSimulacion;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }
    }
}

