package com.juan.nestedsubreport.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private long id;

	@ManyToOne
	@JoinColumn(name = "author")
	@NotNull
	@JsonManagedReference
	private Author authorEntity;

	@OneToMany(mappedBy = "bookEntity")
	private List<Page> pageEntities;

	@DateTimeFormat(pattern = "dd-MMMM-yy")
	private Date releaseDate;

	private String name;

	public Book() {
	}

	public Book(long id, Author authorEntity, List<Page> pageEntities, Date releaseDate, String name) {
		this.id = id;
		this.authorEntity = authorEntity;
		this.pageEntities = pageEntities;
		this.releaseDate = releaseDate;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Author getAuthorEntity() {
		return authorEntity;
	}

	public void setAuthorEntity(Author authorEntity) {
		this.authorEntity = authorEntity;
	}

	public List<Page> getPageEntities() {
		return pageEntities;
	}

	public void setPageEntities(List<Page> pageEntities) {
		this.pageEntities = pageEntities;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Book [id=");
		builder.append(id);
		builder.append(", releaseDate=");
		builder.append(releaseDate);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}

}
