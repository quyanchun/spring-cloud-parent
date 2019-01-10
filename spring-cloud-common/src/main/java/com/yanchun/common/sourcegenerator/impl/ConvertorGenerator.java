package com.yanchun.common.sourcegenerator.impl;




import com.yanchun.common.sourcegenerator.AbstractGenerator;
import com.yanchun.common.sourcegenerator.ContentsFilter;
import com.yanchun.common.sourcegenerator.utils.GeneratorConfiguration;

import java.util.HashMap;
import java.util.Map;

public class ConvertorGenerator extends AbstractGenerator {
    // 模板路径
    private static final String templatePath = "/codetemplate/Convertor.template";

    private ContentsFilter filter;

    private final String templateContents;
    private String setModelCode;
    private String setDTOCode;

    public ConvertorGenerator(final GeneratorConfiguration config) {
        super(config, "convertor");
        this.initConvertingCode();
        this.initFilter();
        this.templateContents = this.getFileString(ConvertorGenerator.templatePath);
    }

    @Override
    public void generate() {
        final String value = this.filter.filter(this.templateContents);
        this.output(value);
    }

    private void initFilter() {
        final Map<String, String> filterMap = new HashMap<String, String>();
        filterMap.put("@Package@", this.getPackage("convertor"));
        filterMap.put("@DTOPath@", this.getClassPath("dto"));
        filterMap.put("@ModelPath@", this.getModelPath());
        filterMap.put("@ServicePath@", this.getClassPath("service"));
        filterMap.put("@Model@", this.getModelName());
        filterMap.put("@model@", this.getModelNameWithHeadLow());
        filterMap.put(" +@setModelCode@", this.setModelCode);
        filterMap.put(" +@setDTOCode@", this.setDTOCode);
        this.filter = new ReplaceFilter(filterMap);
    }

    private void initConvertingCode() {
        final StringBuilder setModelCode = new StringBuilder();
        final StringBuilder setDTOCode = new StringBuilder();
        this.config.getModelProperties().getProperties().forEach(property -> {
            setModelCode.append("        model.").append(property.getSetter()).append("(dto.")
                    .append(property.getGetter())
                    .append("());").append(System.lineSeparator());
            setDTOCode.append("        dto.").append(property.getSetter()).append("(model.")
                    .append(property.getGetter())
                    .append("());").append(System.lineSeparator());
        });
        this.setModelCode = setModelCode.toString();
        this.setDTOCode = setDTOCode.toString();
    }
}
