package pers.ddongx.admin;

import com.github.davidfantasy.mybatisplus.generatorui.GeneratorConfig;
import com.github.davidfantasy.mybatisplus.generatorui.MybatisPlusToolsApplication;
import com.github.davidfantasy.mybatisplus.generatorui.mbp.NameConverter;
import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pers.ddongx.admin.util.LogUtil;

/**
 * 启动类
 *
 * @author Ddong
 */
@SpringBootApplication
public class AdminApplication {

    public static void main(String[] args) {
        try {
            SpringApplication app = new SpringApplication(AdminApplication.class);
            app.run(args);
            initIdGenerator();
            LogUtil.info("项目启动成功ヾ(✿ﾟ▽ﾟ)ノ");
            generate();
        } catch (Exception e) {
            LogUtil.error("项目启动失败(；′⌒`)", e);
            throw e;
        }
    }

    private static void initIdGenerator() {
        // 创建 IdGeneratorOptions 对象，可在构造函数中输入 WorkerId：
        IdGeneratorOptions options = new IdGeneratorOptions();
        // options.WorkerIdBitLength = 10; // 默认值6，限定 WorkerId 最大值为2^6-1，即默认最多支持64个节点。
        // options.SeqBitLength = 6; // 默认值6，限制每毫秒生成的ID个数。若生成速度超过5万个/秒，建议加大 SeqBitLength 到 10。
        // options.BaseTime = Your_Base_Time; // 如果要兼容老系统的雪花算法，此处应设置为老系统的BaseTime。
        // ...... 其它参数参考 IdGeneratorOptions 定义。

        // 保存参数（务必调用，否则参数设置不生效）：
        YitIdHelper.setIdGenerator(options);

        // 以上过程只需全局一次，且应在生成ID之前完成。
    }

    private static void generate() {
        GeneratorConfig config = GeneratorConfig.builder().jdbcUrl("jdbc:mysql://localhost:3306/admin?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true")
                .userName("root")
                .password("Xxd@1234")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                //数据库schema，MSSQL,PGSQL,ORACLE,DB2类型的数据库需要指定
                .schemaName("admin")
                //如果需要修改entity及其属性的命名规则，以及自定义各类生成文件的命名规则，可自定义一个NameConverter实例，覆盖相应的名称转换方法，详细可查看该接口的说明：
                .nameConverter(new NameConverter() {
                    /**
                     * 自定义Service类文件的名称规则
                     */
                    @Override
                    public String serviceNameConvert(String tableName) {
                        return this.entityNameConvert(tableName) + "Service";
                    }

                    /**
                     * 自定义Controller类文件的名称规则
                     */
                    @Override
                    public String controllerNameConvert(String tableName) {
                        return this.entityNameConvert(tableName) + "Controller";
                    }
                })
                //所有生成的java文件的父包名，后续也可单独在界面上设置
                .basePackage("pers.ddongx.admin")
                .port(8068)
                .build();
        MybatisPlusToolsApplication.run(config);
    }

}
