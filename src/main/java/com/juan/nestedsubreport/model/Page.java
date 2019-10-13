package com.juan.nestedsubreport.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Page")
public class Page {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private long id;
	
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "book")
	@NotNull
	private Book bookEntity;
	
	public Page() {
	}

	public Page(long id, String content, Book bookEntity) {
		this.id = id;
		this.content = content;
		this.bookEntity = bookEntity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Book getBookEntity() {
		return bookEntity;
	}

	public void setBookEntity(Book bookEntity) {
		this.bookEntity = bookEntity;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Page [id=");
		builder.append(id);
		builder.append(", content=");
		builder.append(content);
		builder.append("]");
		return builder.toString();
	}
	
}
