<?xml version="1.0"?>
<#import "root://activities/common/kotlin_macros.ftl" as kt>
<recipe>
    <#include "../common/recipe_manifest.xml.ftl" />
    <@kt.addAllKotlinDependencies />

<#if generateLayout>
   <instantiate from="root/res/layout/ArchActivity.xml.ftl"
                to="${escapeXmlAttribute(resOut)}/layout/${layoutName}.xml" />
</#if>

    <instantiate from="root/src/app_package/SimpleActivity.${ktOrJavaExt}.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${activityClass}.${ktOrJavaExt}" />
    <open file="${escapeXmlAttribute(srcOut)}/${activityClass}.${ktOrJavaExt}" />

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
