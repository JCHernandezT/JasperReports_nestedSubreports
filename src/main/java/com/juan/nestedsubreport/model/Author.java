package com.juan.nestedsubreport.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "Author")
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private long id;
	
	private String code;
	
	private String name;
	
	private int age;
	
	@Transient
	private double percentaje;
	
	@OneToMany(mappedBy = "authorEntity")
	@JsonBackReference
	private List<Book> bookEntities;
	
	public Author() {
	}

	public Author(long id, String name, int age, List<Book> bookEntities) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.bookEntities = bookEntities;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<Book> getBookEntities() {
		return bookEntities;
	}

	public void setBookEntities(List<Book> bookEntities) {
		this.bookEntities = bookEntities;
	}
	
	public double getPercentaje() {
		return percentaje;
	}

	public void setPercentaje(double percentaje) {
		this.percentaje = percentaje;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Author [id=");
		builder.append(id);
		builder.append(", code=");
		builder.append(code);
		builder.append(", name=");
		builder.append(name);
		builder.append(", age=");
		builder.append(age);
		builder.append(", percentaje=");
		builder.append(percentaje);
		builder.append("]");
		return builder.toString();
	}
	
}
