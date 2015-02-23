package com.icy.listener;



import java.sql.Time;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.icu.text.SimpleDateFormat;

public class ApplicationInitilizeListener implements ServletContextListener {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ApplicationInitilizeListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.debug("initializing..");
        
        Date date = new Date();
        long date1 = date.getTime();
        System.out.println();
        System.out.println();
        //Comment for commit
        //Another comment for commit
        
        //comment for eclipse commit
//        WebApplicationContext ctx = WebApplicationContextUtils
//                .getWebApplicationContext(sce.getServletContext());
//     
//       System.out.println("----------------------------TEACHER REPOSITORY-----------------------");
//        TeacherRepository teach = ctx.getBean(TeacherRepository.class);
//        ArrayList<Subject> teachSubjects = teach.findTeacherSubjects(teach.findById(1));
//        for(Subject s : teachSubjects)
//        {
//        	System.out.println(s.getName());
//        }
//        
//        
//        System.out.println("-----------------------------SUBJECT TEACHER FOR CLASS REPOSITORY-------------------");
//    	SubjectTeacherForClassRepository stfcRep = ctx.getBean(SubjectTeacherForClassRepository.class);
//    	ArrayList<SubjectTeacherForClass> stfc = (ArrayList<SubjectTeacherForClass>) stfcRep.findAll();
// 
//         for(SubjectTeacherForClass s : stfc)
//         {
//         		System.out.println(s.getTeacher().getFirstName());
//         	
//         }
    	
        }
    
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
    
    public static void main(String args[]){
    
    	try {
    		String now = new SimpleDateFormat("hh:mm aa").format(new java.util.Date().getTime());
            System.out.println("time in 12 hour format : " + now);
            SimpleDateFormat inFormat = new SimpleDateFormat("hh:mm aa");
            SimpleDateFormat outFormat = new SimpleDateFormat("HH:mm");
            String time24 = outFormat.format(inFormat.parse(now));
            System.out.println("time in 24 hour format : " + time24);
            
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            long ms = sdf.parse(time24).getTime();
            Time ttt = new Time(ms);
            System.out.println(ttt);
            
            
            
            
        } catch (Exception e) {
		}
	}
}
