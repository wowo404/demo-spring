package org.liu.demo.mongodb.pojo;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@ToString
@Accessors(chain = true)
@Data
@Document(collection = "company_info_#{T(org.liu.demo.mongodb.util.CollectionNameUtils).getSuffix()}")
public class Company implements Serializable {
    /**
     * ID
     */
    private String id;
    /**
     * 组织机构代码
     */
    @Field("organization_code")
    private String organizationCode;
    /**
     * 社会统一信用代码
     */
    private String socialUniformCreditCode;
    /**
     * 企业名称
     */
    private String name;
    /**
     * 密码
     */
    private String password;
    /**
     * 法定代表人（单位负责人）
     */
    private String corporation;
    /**
     * 单位所在地-区划代码(统计机构填写)
     */
    private String locationCode;
    /**
     * 单位所在地-省(自治区、直辖市)
     */
    private String locationProvince;
    /**
     * 单位所在地-地(区、市、州、盟)
     */
    private String locationCity;
    /**
     * 单位所在地-县(区、市、旗)
     */
    private String locationCounty;
    /**
     * 单位所在地-乡(镇)
     */
    private String locationVillages;
    /**
     * 单位所在地-街(村)、门牌号
     */
    private String locationBlock;
    /**
     * 单位所在地-街道办事处
     */
    private String locationBlockOffice;
    /**
     * 单位所在地-社区(居委会)
     */
    private String locationCommunity;
    /**
     * 单位注册地-区划代码(统计机构填写)
     */
    private String registrationCode;
    /**
     * 单位注册地-省(自治区、直辖市)
     */
    private String registrationProvince;
    /**
     * 单位注册地-地(区、市、州、盟)
     */
    private String registrationCity;
    /**
     * 单位注册地-县(区、市、旗)
     */
    private String registrationCounty;
    /**
     * 单位注册地-乡(镇)
     */
    private String registrationVillages;
    /**
     * 单位注册地-街(村)、门牌号
     */
    private String registrationBlock;
    /**
     * 单位注册地-街道办事处
     */
    private String registrationBlockOffice;
    /**
     * 单位注册地-社区(居委会)
     */
    private String registrationCommunity;
    /**
     * 联系方式-长途区号
     */
    private String contactWayAreaCode;
    /**
     * 联系方式-固定电话
     */
    private String contactWayTelephone;
    /**
     * 联系方式-移动电话
     */
    private String contactWayMobilephone;
    /**
     * 联系方式-传真号码
     */
    private String contactWayFax;
    /**
     * 联系方式-邮政编码
     */
    private String contactWayPostalCode;
    /**
     * 联系方式-电子邮箱
     */
    private String contactWayEmail;
    /**
     * 联系方式-网址
     */
    private String contactWayWebsite;
    /**
     * 行业类别-主要业务活动个数
     */
    private String industryCategoryNum;
    /**
     * 行业类别-主要业务活动(或主要产品)
     */
    private String[] industryCategoryName = new String[0];
    /**
     * 行业代码
     */
    private String industryCode;
    /**
     * 登记注册类型
     */
    private String registrationType;
    /**
     * 企业控股情况
     */
    private String companyHoldings;
    /**
     * 隶属关系
     */
    private String membershipRelation;
    /**
     * 开业(成立)时间
     */
    private String startBusinessTime;
    /**
     * 营业状态
     */
    private String businessStatus;
    /**
     * 执行会计标准类别
     */
    private String accountingStandardCategories;
    /**
     * 执行企业会计准则情况
     */
    private String accountingStandardsForEnterprises;
    /**
     * 机构类型
     */
    private String organizationType;
    /**
     * 产业活动单位数-总数
     */
    private String industrialActivityNumTotal;
    /**
     * 产业活动单位数-农林牧渔业
     */
    private String industrialActivityNumNlmyy;
    /**
     * 产业活动单位数-工业
     */
    private String industrialActivityNumIndustrial;
    /**
     * 产业活动单位数-建筑业
     */
    private String industrialActivityNumArchitecture;
    /**
     * 产业活动单位数-批发和零售业
     */
    private String industrialActivityNumWholesaleAndRetail;
    /**
     * 产业活动单位数-住宿和餐饮业
     */
    private String industrialActivityNumAccommodationCatering;
    /**
     * 产业活动单位数-房地产业
     */
    private String industrialActivityNumRealEstate;
    /**
     * 产业活动单位数-其他
     */
    private String industrialActivityNumOther;
    /**
     * 从业人员-期末人数
     */
    private String practitionerFinalNum;
    /**
     * 从业人员-女性
     */
    private String practitionerFemaleNum;
    /**
     * 企业主要经济指标-营业收入
     */
    private String mainEconomicIndicatorsOperationRevenue;
    /**
     * 企业主要经济指标-营业税金及附加
     */
    private String mainEconomicIndicatorsTaxSurcharges;
    /**
     * 企业主要经济指标-主营业收入
     */
    private String mainEconomicIndicatorsMainOperationRevenue;
    /**
     * 企业主要经济指标-资产总计
     */
    private String mainEconomicIndicatorsAssetsTotal;
    /**
     * 企业主要经济指标-主营业务税金及附加
     */
    private String mainEconomicIndicatorsMainBusinessTaxExtraCharges;
    /**
     * 非企业单位支出(费用)
     */
    private String nonbusinessOfficeExpend;
    /**
     * 年末资产
     */
    private String yearEndAsset;
    /**
     * 企业集团情况(限企业集团母公司及成员企业填写)
     */
    private String enterpriseGroupSituation;
    /**
     * 成员企业请填直接上级法人单位组织机构代码
     */
    private String higherCourtOrganizationCode;
    /**
     * 上级法人统一社会信用代码
     */
    private String higherCourtSocialUniformCreditCode;
    /**
     * 建筑业及房地产业企业资质等级-建筑业企业资质等级编码（有资质的企业，请填写建筑业企业资质等级编码，没有资质的填‘9999’）
     */
    private String qualificationLevelConstruction;
    /**
     * 建筑业及房地产业企业资质等级-房地产开发经营业企业资质等级
     */
    private String qualificationLevelRealEstate;
    /**
     * 建筑业及房地产业企业资质等级-物业管理业企业资质等级
     */
    private String qualificationLevelTenement;
    /**
     * 批发和零售业企业经营形式
     */
    private String wholesaleAndRetailBusinessForms;
    /**
     * 零售业态
     */
    private String retailFormat;
    /**
     * 批发和零售业年末零售营业面积
     */
    private String wholesaleAndRetailBusinessFormsArea;
    /**
     * 住宿和餐饮业企业经营形式
     */
    private String accommodationAndCateringBusinessForms;
    /**
     * 住宿业企业星级评定情况
     */
    private String starRatingOfAccommodationIndustryEnterprises;
    /**
     * 住宿和餐饮业年末餐饮营业面积
     */
    private String accommodationAndCateringBusinessFormsArea;
    /**
     * 企业分类Id
     */
    private Long companyCategoryId;
    /**
     * 审核状态(0未审核 1审核通过 2审核不通过)
     */
    private Integer auditStatus;
    /**
     * 删除标志（0代表存在 1代表删除）
     */
    private Integer delFlag;

    /**
     * 关联企业用户id
     */
    private Long relationUserId;
}

