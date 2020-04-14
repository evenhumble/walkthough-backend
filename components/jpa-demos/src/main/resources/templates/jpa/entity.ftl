package ${packageName}.entity;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

/**
*  ${classInfo.classComment}
* @author ${authorName} ${.now?string('yyyy-MM-dd')}
*/
@Entity
@Data
@Table(name="${classInfo.tableName}")
public class ${classInfo.className} implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
<#if classInfo.fields?exists && classInfo.fields?size gt 0>
<#list classInfo.fields as fieldItem >
    /**
    * ${fieldItem.fieldComment}
    */
    private ${fieldItem.fieldType} ${fieldItem.fieldName};

</#list>

</#if>

}