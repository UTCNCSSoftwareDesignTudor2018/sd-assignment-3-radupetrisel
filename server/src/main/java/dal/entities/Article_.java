package dal.entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Article.class)
public class Article_ {
	
	public static volatile SingularAttribute<Article, String> title;
	public static volatile SingularAttribute<Article, User> author;
	public static volatile SingularAttribute<Article, String> articleAbstract;
	public static volatile SingularAttribute<Article, String> body;
	
}
