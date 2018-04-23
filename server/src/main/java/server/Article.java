package server;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "articles")
public class Article {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column
	private String title;
	
	@Column(name = "abstract")
	private String articleAbstract;
	
	@Column
	private String body;
	
	@OneToOne
	@JoinColumn
	private Author author;
	
	
}
