package cn.edu.zucc.chenxg.model;

public class JPAUtil {

    public static final String JPA = "test";
    public static final String SELECT_PREVIEW_BY_SID= "select * from filepreview where sid=?";
    public static final String SELECT_PREVIEW_BY_USER = "select * from filepreview where uploaduserid=?";
    public static final String SELECT_USERINFO_BY_USERID = "select * from userinfo where userid=?";
    
    public static final String SELECT_CRITERIA_BY_LEVLE = "select * from public.cri where level=? or level='all' ";
    public static final String SELECT_TALENTREVIEWSCORE_BY_REVIEWER = "select * from public.talent_review_score where reviewer_id=? order by reviewer_id,employee_id";
    public static final String SELECT_TALENTREVIEWSCORE_BY_PMO = "select  * from public.talent_review_score where pmo_id=?";
    public static final String SELECT_REVIEWER_BY_PMO = "select distinct reviewer_id from public.talent_review_score where pmo_id=?";
    public static final String SELECT_MAX_REVIEW_PERIOD = "select review_period from public.rp where id=(select max(id) from public.rp)";

    private JPAUtil() {
    }
}