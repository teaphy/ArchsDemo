<?xml version="1.0"?>
<#import "root://activities/common/kotlin_macros.ftl" as kt>
<recipe>
    <@kt.addAllKotlinDependencies />
    <#if useSupport><dependency mavenUrl="com.android.support:support-v4:${buildApi}.+"/></#if>
    <merge from="root/res/values/strings.xml" to="${escapeXmlAttribute(resOut)}/values/strings.xml" />

    <#if includeLayout>
        <instantiate from="root/res/layout/fragment_blank.xml.ftl"
                       to="${escapeXmlAttribute(resOut)}/layout/${escapeXmlAttribute(fragmentName)}.xml" />

        <open file="${escapeXmlAttribute(resOut)}/layout/${escapeXmlAttribute(fragmentName)}.xml" />
    </#if>

    <instantiate from="root/src/app_package/BlankFragment.${ktOrJavaExt}.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${className}.${ktOrJavaExt}" />

    <open file="${escapeXmlAttribute(srcOut)}/${className}.${ktOrJavaExt}" />

    <#if generateViewModel>
        <instantiate from="root/src/app_package/SimpleViewModel.${ktOrJavaExt}.ftl"
                    to="${escapeXmlAttribute(srcOut)}/${viewModel}ViewModel.${ktOrJavaExt}" />
        <open file="${escapeXmlAttribute(srcOut)}/${viewModel}ViewModel.${ktOrJavaExt}" />                   
        <#if generateResponse>
            <instantiate from="root/src/app_package/SimpleResponse.${ktOrJavaExt}.ftl"
                        to="${escapeXmlAttribute(srcOut)}/${viewModel}Response.${ktOrJavaExt}" />
            <open file="${escapeXmlAttribute(srcOut)}/${viewModel}Response.${ktOrJavaExt}" />
        </#if>
    </#if>

    <#if generateAdapter>
        <instantiate from="root/src/app_package/SimpleAdapter.${ktOrJavaExt}.ftl"
                    to="${escapeXmlAttribute(srcOut)}/${adapterClass}Adapter.${ktOrJavaExt}" />
        <open file="${escapeXmlAttribute(srcOut)}/${adapterClass}Adapter.${ktOrJavaExt}" />
        
        <instantiate from="root/src/app_package/SimpleDiff.${ktOrJavaExt}.ftl"
                    to="${escapeXmlAttribute(srcOut)}/${adapterClass}Diff.${ktOrJavaExt}" />
        <open file="${escapeXmlAttribute(srcOut)}/${adapterClass}Diff.${ktOrJavaExt}" />
        
        <instantiate from="root/res/layout/ArchAdapter.xml.ftl"
                    to="${escapeXmlAttribute(resOut)}/layout/item_${adapterLayoutName}.xml" />
    </#if>
</recipe>
