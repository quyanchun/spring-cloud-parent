package com.yanchun.common.sourcegenerator.impl;



import com.yanchun.common.sourcegenerator.AbstractGenerator;
import com.yanchun.common.sourcegenerator.ContentsFilter;
import com.yanchun.common.sourcegenerator.utils.GeneratorConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DtoGenerator extends AbstractGenerator {

    private ContentsFilter filter;

    private final String modelSource;

    public DtoGenerator(final GeneratorConfiguration config) {
        super(config, "dto");
        final String modelSource = this.getFileString(this.config.getModelSrcPath());
        this.modelSource = this.initApiDocs(modelSource);
        this.initFilter();
    }

    // 初始化API文档信息
    private String initApiDocs(final String source) {
        final Pattern pattern = Pattern.compile("(/\\*\\*\\s+\\*\\s)(.+)(\\s+\\*/)");
        final String result = source;
        final Matcher matcher = pattern.matcher(source);
        //        while (matcher.find()) {
        //            final String originalStr = matcher.group(0);
        //            final String docStr = matcher.group(2);
        //            result = result.replace(originalStr,
        //                    originalStr + System.lineSeparator() + "    @ApiModelProperty(\"" + docStr + "\")");
        //        }
        return result;
    }

    @Override
    public void generate() {
        final String value = this.filter.filter(this.modelSource);
        this.output(value);
    }

    private void initFilter() {
        final Map<String, String> filterMap = new HashMap<String, String>();
        final String packageStr = "package " + this.getPackage("dto") + ";" + System.lineSeparator()
                /*+ "import com.morefun.phili.common.common.dto.AbstractAuditDTO;"*/;
        filterMap.put("package.+;", packageStr);
        filterMap.put("import javax\\.persistence.+\\s+", "");
        filterMap.put("import com\\.leadingsoft\\.bizfuse\\.common\\.jpa\\.model.+",
                "import io.swagger.annotations.ApiModelProperty;");
        filterMap.put("@Entity\\s+", "");
        filterMap.put("@Table.+\\s+", "");
        filterMap.put("@Basic\\s+", "");
        filterMap.put("@Id\\s+", "");
        filterMap.put("\\s.+serialVersionUID.+\\s+", "");
        filterMap.put("\\n\\s+@Column.+", "");
        filterMap.put("\\n\\s+@Enumerated.+", "");
        filterMap.put("\\n\\s+@GeneratedValue.+", "");
        filterMap.put("\\n\\s+@OneToOne.+", "");
        filterMap.put("\\n\\s+@OneToMany.+", "");
        filterMap.put("\\n\\s+@ManyToMany.+", "");
        filterMap.put("\\n\\s+@ManyToOne.+", "");
        filterMap.put("\\n\\s+@Table.+", "");
        filterMap.put("\\n\\s+@Temporal.+", "");
        final String dtoClass = "public class @Model@DTO  {".replace("@Model@",
                this.config.getModelClazz().getSimpleName());
        filterMap.put("public class.+\\{", dtoClass);
        this.filter = new ReplaceFilter(filterMap);
    }

}
