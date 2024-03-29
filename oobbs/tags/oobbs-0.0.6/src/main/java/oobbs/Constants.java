package oobbs;


/**
 * Constant values used throughout the application.
 * 
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class Constants {
    
    /**
     * The name of the ResourceBundle used in this application
     */
    public static final String BUNDLE_KEY = "ApplicationResources";

    /**
     * File separator from System properties
     */
    public static final String FILE_SEP = System.getProperty("file.separator");

    /**
     * User home from System properties
     */
    public static final String USER_HOME = System.getProperty("user.home") + FILE_SEP;

    /**
     * The name of the configuration hashmap stored in application scope.
     */
    public static final String CONFIG = "appConfig";

    /**
     * Session scope attribute that holds the locale set by the user. By setting this key
     * to the same one that Struts uses, we get synchronization in Struts w/o having
     * to do extra work or have two session-level variables.
     */
    public static final String PREFERRED_LOCALE_KEY = "org.apache.struts2.action.LOCALE";

    /**
     * The request scope attribute under which an editable user form is stored
     */
    public static final String USER_KEY = "userForm";

    /**
     * The request scope attribute that holds the user list
     */
    public static final String USER_LIST = "userList";

    /**
     * The request scope attribute for indicating a newly-registered user
     */
    public static final String REGISTERED = "registered";

    /**
     * The name of the Administrator role, as specified in web.xml
     */
    public static final String ADMIN_ROLE = "ROLE_ADMIN";

    /**
     * The name of the User role, as specified in web.xml
     */
    public static final String USER_ROLE = "ROLE_USER";

    /**
     * The name of the user's role list, a request-scoped attribute
     * when adding/editing a user.
     */
    public static final String USER_ROLES = "userRoles";

    /**
     * The name of the available roles list, a request-scoped attribute
     * when adding/editing a user.
     */
    public static final String AVAILABLE_ROLES = "availableRoles";

    /**
     * The name of the CSS Theme setting.
     */
    public static final String CSS_THEME = "csstheme";
    
    public static final String POST_MODE = "postMode";
    
    public static final String CREATE_THREAD = "createThread";
    
    public static final String CREATE_REPLY = "createReply";
    
    public static final String UPDATE_POST = "updatePost";
    
    public static final int THREAD_PAGING_SIZE = 10;
    
    public static final int POST_PAGING_SIZE = 10;
    
    public static final String PAGER = "pager";
    
    public static final String ACT_GET_ALL_FORUM_GROUPS="getAllForumGroups";
    
    public static final String ACT_GET_FORUM_GROUP_BY_ID="getForumGroupById";
    
    public static final String ACT_GET_FORUM_BY_ID="getForumById";
    
    public static final String ACT_GET_THREAD_BY_ID="getThreadById";
    
    public static final String REQ_ATTRIB_NAVIGATION_PATH="navigationPath";
    
    public static final String REQ_PARAM_FORUM_GROUP_ID="forumGroupId";
    
    public static final String REQ_PARAM_FORUM_ID="forumId";
    
    public static final String REQ_PARAM_THREAD_ID="threadId";
    
    /* -------------------------------------   Domain Object Listener Name   ------------------------------------*/
    
    public static final String FORUM_REPO_AS_FORUM_LISTENER = "FORUM_REPO_AS_FORUM_LISTENER";
    
    public static final String THREAD_REPO_AS_THREAD_LISTENER = "THREAD_REPO_AS_THREAD_LISTENER";
    
    /* -------------------------------------   Domain Requests   ------------------------------------*/
    
    public static final String FORUM_ID ="forumId";
   
}
