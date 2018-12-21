package br.ufrpe.flight_system.negocio.beans;

public enum GmtZoneId {
    GMT1N("GMT-01:00"), GMT2N("GMT-02:00"), GMT3N("GMT-03:00"), GMT4N("GMT-04:00"), 
    GMT5N("GMT-05:00"), GMT6N("GMT-06:00"), GMT7N("GMT-07:00"), GMT8N("GMT-08:00"), 
    GMT9N("GMT-09:00"), GMT10N("GMT-10:00"), GMT11N("GMT-11:00"), GMT12N("GMT-12:00"),
    GMT("GMT"), 
    GMT1P("GMT+01:00"), GMT2P("GMT+02:00"), GMT3P("GMT+03:00"), GMT4P("GMT+04:00"), 
    GMT5P("GMT+05:00"), GMT6P("GMT+06:00"), GMT7P("GMT+07:00"), GMT8P("GMT+08:00"), 
    GMT9P("GMT+09:00"), GMT10P("GMT+10:00"), GMT11P("GMT+11:00"), GMT12P("GMT+12:00");

    GmtZoneId(String value) {
        this.value = value;
    }
    
    private String value;
    
    @Override
    public String toString() {
        return this.value;
    }
}
