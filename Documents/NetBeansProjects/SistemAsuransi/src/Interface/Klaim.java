
package Interface;

import java.sql.Date;

public class Klaim {
    private int id;
    private int polisId;
    private Date tanggal;
    private String status;

    public Klaim(int polisId, Date tanggal, String status) {
        this.polisId = polisId;
        this.tanggal = tanggal;
        this.status = status;
    }

    // Getter dan Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPolisId() {
        return polisId;
    }

    public void setPolisId(int polisId) {
        this.polisId = polisId;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Polis ID: " + polisId + 
               ", Tanggal: " + tanggal + ", Status: " + status;
    }
}
