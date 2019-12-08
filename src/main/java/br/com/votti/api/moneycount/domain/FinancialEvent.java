package br.com.votti.api.moneycount.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@EqualsAndHashCode(of={"code"})
public class FinancialEvent {

	private String code;
	private String description;
	private List<FinancialEntry> entries;
	private Currency currency;
	
	private FinancialEvent(String code, String description, Currency currency) {
		this.code = code;
		this.description = description;
		this.currency = currency;
		this.entries = new ArrayList<>();
	}
	
	public void addFinancialEntry(FinancialEntry entry) {
		this.entries.add(entry);
	}
	
	public List<FinancialEntry> getEntries(){
		return Collections.unmodifiableList(entries);
	}
	
	public BigDecimal getValue() {
		BigDecimal totalValue = entries.stream()
			.map(FinancialEntry::getValue)
			.reduce(BigDecimal.ZERO, BigDecimal::add);
		return totalValue;
	}

}
