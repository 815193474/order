package vip.bigeye.order.Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @Author wolf  VX:a815193474
 * @Data 2020-03-21 10:44
 */
public class DownController {

    public void down(HttpServletRequest request, HttpServletResponse response,String fileUrl,String fileName){
        //创建输入输出流
        BufferedOutputStream bos=null;
        BufferedInputStream bis=null;

        try{
            //设置请求头
            response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode(fileName,"UTF-8"));
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("UTF-8");

            //将文件交给输入流
            fileUrl=fileUrl+fileName;
            bis=new BufferedInputStream( new FileInputStream(fileUrl));
            //通知相应体获取文件输出流
            bos=new BufferedOutputStream(response.getOutputStream());
            //定义二进制缓存池
            byte[] b=new byte[2048];
            int bufferred;

            while(-1!=(bufferred=bis.read(b,0,b.length))){
                bos.write(b,0,bufferred);

            }
            bos.flush();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
                //关闭输入流
            if (bis!=null){
                try{
                    bis.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            bis=null;
            //关闭输出流
            if (bos!=null){
                try{
                    bos.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
                bos=null;
            }

            //删除服务器缓存文件
            File file=new File(fileUrl);
            file.delete();
        }





    }
}
