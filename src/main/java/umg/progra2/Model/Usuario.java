package umg.progra2.Model;

public class Usuario {
    private int idUsuario;
    private String carne;
    private String nombre;
    private String correo;
    private String seccion;
    private long telegramId;
    private String activo;

    public Usuario() {
    }
    public Usuario(int idUsuario, String carne, String nombre, String correo, String seccion, long telegramId, String activo) {
        this.idUsuario = idUsuario;
        this.carne = carne;
        this.nombre = nombre;
        this.correo = correo;
        this.seccion = seccion;
        this.telegramId = telegramId;
        this.activo = activo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCarne() {
        return carne;
    }

    public void setCarne(String carne) {
        this.carne = carne;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public long getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(long telegramId) {
        this.telegramId = telegramId;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }
}