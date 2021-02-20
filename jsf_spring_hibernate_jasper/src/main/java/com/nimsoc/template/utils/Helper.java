package com.nimsoc.template.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class Helper {

  public static void closeQuietly(AutoCloseable res) {
    if (res != null) {
      try {
        res.close();
      } catch (Exception ex) {
        Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  public static void closeQuietly(Connection res) {
    if (res != null) {
      try {
        res.close();
      } catch (SQLException ex) {
        Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  public static void closeQuietly(Statement res) {
    if (res != null) {
      try {
        res.close();
      } catch (SQLException ex) {
        Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  public static void closeQuietly(ResultSet res) {
    if (res != null) {
      try {
        res.close();
      } catch (SQLException ex) {
        Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
  
  public static String getHttpSessionID() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession thisSession = (HttpSession) fc.getExternalContext().getSession(false);
    return thisSession == null ? null : thisSession.getId();
  }

  public static void invalidateSession() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession thisSession = (HttpSession) fc.getExternalContext().getSession(false);
    if (thisSession != null) {
      thisSession.invalidate();
    }
  }
}
