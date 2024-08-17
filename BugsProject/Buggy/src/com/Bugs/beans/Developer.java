package com.Bugs.beans;
import java.util.List;

public class Developer extends Users {      //implements DeveloperDao to be added and moved to Dao folder

    List <Project> projectLists= Project.getAllProjects();  //+Aman

    public Developer(long userId,String userName,String email,String userType){
        super(userId,userName,email,userType);
    }


    public void viewProject(long projectID) {
        //+Added
        boolean found=false;
        for (Project project : projectLists) {

            if (project.getProjectId() == projectID) {
                System.out.println("Project ID: " + project.getProjectId());
                System.out.println("Project Name: " + project.getProjectName());
                System.out.println("Manager ID: " + project.getManagerId());
                System.out.println("Start Date: " + project.getStartDate());
                System.out.println("Project Status: " + project.getProjectStatus());
                System.out.println("Start Date: " + project.getStartDate());

                found=true;
            }
        }

        if(!found){
           System.out.println("Project not found");
       }

    }

    //+Added
    public String fixBug(Bug bug){
        System.out.println(bug.toString());
        return "Bug fixed, Pls verify and update status";
    }


    public void markClose(){
        // applies to the project manager to fix the bug status
    }

    public void validateMarkClose(){
        // validates if the bug is fixed
    }


}
