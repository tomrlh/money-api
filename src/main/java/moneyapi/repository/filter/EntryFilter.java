package moneyapi.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class EntryFilter {

	private String description;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dueDateSince;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dueDateUntil;
	
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDueDateSince() {
		return dueDateSince;
	}
	public void setDueDateSince(LocalDate dueDateSince) {
		this.dueDateSince = dueDateSince;
	}
	public LocalDate getDueDateUntil() {
		return dueDateUntil;
	}
	public void setDueDateUntil(LocalDate dueDateUntil) {
		this.dueDateUntil = dueDateUntil;
	}
}
