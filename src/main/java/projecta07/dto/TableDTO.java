package projecta07.dto;

import javax.persistence.Column;

public class TableDTO {
    private Long idTable;
    private String codeTable;
    private Boolean emptyTable;
    private String nameStatus;
    private Long idStatus;

    public TableDTO() {
    }

    public TableDTO(Long idTable, String codeTable, Boolean emptyTable, String nameStatus, Long idStatus) {
        this.idTable = idTable;
        this.codeTable = codeTable;
        this.emptyTable = emptyTable;
        this.nameStatus = nameStatus;
        this.idStatus = idStatus;
    }

    public Long getIdTable() {
        return idTable;
    }

    public void setIdTable(Long idTable) {
        this.idTable = idTable;
    }

    public String getCodeTable() {
        return codeTable;
    }

    public void setCodeTable(String codeTable) {
        this.codeTable = codeTable;
    }

    public Boolean getEmptyTable() {
        return emptyTable;
    }

    public void setEmptyTable(Boolean emptyTable) {
        this.emptyTable = emptyTable;
    }

    public String getNameStatus() {
        return nameStatus;
    }

    public void setNameStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }

    public Long getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Long idStatus) {
        this.idStatus = idStatus;
    }
}
