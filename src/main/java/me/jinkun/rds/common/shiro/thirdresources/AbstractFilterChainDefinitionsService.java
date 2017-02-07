package me.jinkun.rds.common.shiro.thirdresources;

import org.apache.shiro.config.Ini;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * 安全框架角色资源配置服务类
 *
 * @author shadow
 */
public abstract class AbstractFilterChainDefinitionsService implements FilterChainDefinitionsService {

    private final static Logger log = LoggerFactory.getLogger(AbstractFilterChainDefinitionsService.class);

    private String definitions = "";

    @Autowired
    private ShiroFilterFactoryBean shiroFilterFactoryBean;

    @PostConstruct
    public void intiPermission() {
        shiroFilterFactoryBean.setFilterChainDefinitionMap(obtainPermission());
        log.debug("initialize shiro permission success...");
    }

    public void updatePermission() {

        synchronized (shiroFilterFactoryBean) {

            AbstractShiroFilter shiroFilter = null;

            try {
                shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
            } catch (Exception e) {
                log.error(e.getMessage());
            }

            // 获取过滤管理器  
            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter
                    .getFilterChainResolver();
            DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();

            // 清空初始权限配置  
            manager.getFilterChains().clear();
            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();

            // 重新构建生成  
            shiroFilterFactoryBean.setFilterChainDefinitions(definitions);
            Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();

            for (Map.Entry<String, String> entry : chains.entrySet()) {
                String url = entry.getKey();
                String chainDefinition = entry.getValue().trim().replace(" ", "");
                manager.createChain(url, chainDefinition);
            }

            log.debug("update shiro permission success...");
        }
    }

    /**
     * 读取配置资源
     */
    private Ini.Section obtainPermission() {
        Ini ini = new Ini();
        ini.load(definitions); // 加载资源文件节点串  
        Ini.Section section = ini.getSection("urls"); // 使用默认节点
        if (CollectionUtils.isEmpty(section)) {
            section = ini.getSection(Ini.DEFAULT_SECTION_NAME); // 如不存在默认节点切割,则使用空字符转换  
        }
        Map<String, String> permissionMap = initOtherPermission();
        if (permissionMap != null && !permissionMap.isEmpty()) {
            section.putAll(permissionMap);
        }
        return section;
    }

    public abstract Map<String, String> initOtherPermission();

    public String getDefinitions() {
        return definitions;
    }

    public void setDefinitions(String definitions) {
        this.definitions = definitions;
    }
}