package dal.entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ArticleEntity.class)
public class ArticleEntity_ {
	
	public static volatile SingularAttribute<ArticleEntity, String> title;
	public static volatile SingularAttribute<ArticleEntity, UserEntity> author;
	public static volatile SingularAttribute<ArticleEntity, String> articleAbstract;
	public static volatile SingularAttribute<ArticleEntity, String> body;
	
}
