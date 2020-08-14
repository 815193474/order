package vip.bigeye.order.Utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.ui.ModelMap;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * @Author wolf  VX:a815193474
 * @Data 2020-03-21 12:08
 */
public class CreateFileController {

    public void createFile(OutputStream out, String template, Object props){

        Writer writer=null;
        //建立关联工具
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
        // 模板加载路径
        configuration.setClassForTemplateLoading(CreateFileController.class, "/templates/ftl");
        Template t = null;
        try {
            t = configuration.getTemplate(template);
            writer = new OutputStreamWriter(out, "utf-8");
            t.process(props, writer);
            writer.flush();

        } catch (Exception ex) {
            ex.getMessage();
        } finally {
            if (null != writer) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.getMessage();
                }
            }
        }

    }

}
