package it.unicam.ids.dciotti.downtowntour.model;

import java.util.Date;
import java.util.Objects;

public class Report {
    private final Tourist tourist;
    private final Content content;
    private boolean solved = false;
    private final Date timestamp = new Date();

    public Report(Tourist tourist, Content content) {
        this.tourist = tourist;
        this.content = content;
        content.getReportToBeSolved().add(this);
    }

    public Tourist getTourist() {
        return tourist;
    }

    public Content getContent() {
        return content;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
        if (solved) {
            content.getReportToBeSolved().remove(this);
        } else {
            content.getReportToBeSolved().add(this);
        }
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Report report)) return false;
        return isSolved() == report.isSolved() && Objects.equals(getTourist(), report.getTourist()) && Objects.equals(getContent(), report.getContent()) && Objects.equals(getTimestamp(), report.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTourist(), getContent(), isSolved(), getTimestamp());
    }
}
