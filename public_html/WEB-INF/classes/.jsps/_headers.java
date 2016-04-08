
import oracle.jsp.runtime.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import oracle.jsp.el.*;
import javax.servlet.jsp.el.*;


public class _headers extends com.orionserver.http.OrionHttpJspPage {


  // ** Begin Declarations


  // ** End Declarations

  public void _jspService(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException, ServletException {

    response.setContentType( "text/html;charset=windows-1252");
    /* set up the intrinsic variables using the pageContext goober:
    ** session = HttpSession
    ** application = ServletContext
    ** out = JspWriter
    ** page = this
    ** config = ServletConfig
    ** all session/app beans declared in globals.jsa
    */
    PageContext pageContext = JspFactory.getDefaultFactory().getPageContext( this, request, response, null, true, JspWriter.DEFAULT_BUFFER, true);
    // Note: this is not emitted if the session directive == false
    HttpSession session = pageContext.getSession();
    int __jsp_tag_starteval;
    ServletContext application = pageContext.getServletContext();
    JspWriter out = pageContext.getOut();
    _headers page = this;
    ServletConfig config = pageContext.getServletConfig();
    javax.servlet.jsp.el.VariableResolver __ojsp_varRes = (VariableResolver)new OracleVariableResolverImpl(pageContext);

    try {


      out.write(__oracle_jsp_text[0]);
      out.write(__oracle_jsp_text[1]);
      out.write(__oracle_jsp_text[2]);
      {
        org.apache.taglibs.standard.tag.rt.core.ForEachTag __jsp_taghandler_1=(org.apache.taglibs.standard.tag.rt.core.ForEachTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.taglibs.standard.tag.rt.core.ForEachTag.class,"org.apache.taglibs.standard.tag.rt.core.ForEachTag items var");
        __jsp_taghandler_1.setParent(null);
        __jsp_taghandler_1.setItems((java.lang.Object)oracle.jsp.runtime.OracleJspRuntime.evaluate("${headerValues}",java.lang.Object.class, __ojsp_varRes,null));
        __jsp_taghandler_1.setVar("h");
        try {
          __jsp_tag_starteval=__jsp_taghandler_1.doStartTag();
          if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
          {
            do {
              out.write(__oracle_jsp_text[3]);
              {
                org.apache.taglibs.standard.tag.rt.core.OutTag __jsp_taghandler_2=(org.apache.taglibs.standard.tag.rt.core.OutTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.taglibs.standard.tag.rt.core.OutTag.class,"org.apache.taglibs.standard.tag.rt.core.OutTag value");
                __jsp_taghandler_2.setParent(__jsp_taghandler_1);
                __jsp_taghandler_2.setValue((java.lang.Object)oracle.jsp.runtime.OracleJspRuntime.evaluate("${h.key}",java.lang.Object.class, __ojsp_varRes,null));
                __jsp_tag_starteval=__jsp_taghandler_2.doStartTag();
                if (__jsp_taghandler_2.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                  return;
                OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_2,2);
              }
              out.write(__oracle_jsp_text[4]);
              {
                org.apache.taglibs.standard.tag.rt.core.ForEachTag __jsp_taghandler_3=(org.apache.taglibs.standard.tag.rt.core.ForEachTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.taglibs.standard.tag.rt.core.ForEachTag.class,"org.apache.taglibs.standard.tag.rt.core.ForEachTag items var");
                __jsp_taghandler_3.setParent(__jsp_taghandler_1);
                __jsp_taghandler_3.setItems((java.lang.Object)oracle.jsp.runtime.OracleJspRuntime.evaluate("${h.value}",java.lang.Object.class, __ojsp_varRes,null));
                __jsp_taghandler_3.setVar("value");
                try {
                  __jsp_tag_starteval=__jsp_taghandler_3.doStartTag();
                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                  {
                    do {
                      out.write(__oracle_jsp_text[5]);
                      {
                        org.apache.taglibs.standard.tag.rt.core.OutTag __jsp_taghandler_4=(org.apache.taglibs.standard.tag.rt.core.OutTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.taglibs.standard.tag.rt.core.OutTag.class,"org.apache.taglibs.standard.tag.rt.core.OutTag value");
                        __jsp_taghandler_4.setParent(__jsp_taghandler_3);
                        __jsp_taghandler_4.setValue((java.lang.Object)oracle.jsp.runtime.OracleJspRuntime.evaluate("${value}",java.lang.Object.class, __ojsp_varRes,null));
                        __jsp_tag_starteval=__jsp_taghandler_4.doStartTag();
                        if (__jsp_taghandler_4.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                          return;
                        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_4,3);
                      }
                      out.write(__oracle_jsp_text[6]);
                    } while (__jsp_taghandler_3.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
                  }
                  if (__jsp_taghandler_3.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                    return;
                } catch (Throwable th) {
                  __jsp_taghandler_3.doCatch(th);
                } finally {
                  __jsp_taghandler_3.doFinally();
                }
                OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_3,2);
              }
              out.write(__oracle_jsp_text[7]);
            } while (__jsp_taghandler_1.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
          }
          if (__jsp_taghandler_1.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
            return;
        } catch (Throwable th) {
          __jsp_taghandler_1.doCatch(th);
        } finally {
          __jsp_taghandler_1.doFinally();
        }
        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_1,1);
      }
      out.write(__oracle_jsp_text[8]);


    }
    catch( Throwable e) {
      if (!(e instanceof javax.servlet.jsp.SkipPageException)){
        try {
          if (out != null) out.clear();
        }
        catch( Exception clearException) {
        }
        pageContext.handlePageException( e);
      }
    }
    finally {
      OracleJspRuntime.extraHandlePCFinally(pageContext,true);
      JspFactory.getDefaultFactory().releasePageContext(pageContext);
    }

  }
  private static final char __oracle_jsp_text[][]=new char[9][];
  static {
    try {
    __oracle_jsp_text[0] = 
    "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\n\"http://www.w3.org/TR/html4/loose.dtd\">\n".toCharArray();
    __oracle_jsp_text[1] = 
    "\n".toCharArray();
    __oracle_jsp_text[2] = 
    "\n<html>\n  <head>\n    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1252\"/>\n    <title>Request Info</title>\n    <style type=\"text/css\">\n      body {\n      background-color: #ffffff; \n}\n    </style>\n  </head>\n  <body>\n    <p>\n      <a href=\"../Shibboleth.sso/Login?target=https://webservices.library.ucla.edu/catalog/headers.jsp\">Login</a>\n    </p>\n    Headers:\n    <ul>\n      ".toCharArray();
    __oracle_jsp_text[3] = 
    "\n        <li>\n          ".toCharArray();
    __oracle_jsp_text[4] = 
    "\n          <ul>\n            ".toCharArray();
    __oracle_jsp_text[5] = 
    "\n              <li>\n                ".toCharArray();
    __oracle_jsp_text[6] = 
    "\n              </li>\n            ".toCharArray();
    __oracle_jsp_text[7] = 
    "\n          </ul>\n        </li>\n      ".toCharArray();
    __oracle_jsp_text[8] = 
    "\n    </ul>\n  </body>\n</html>".toCharArray();
    }
    catch (Throwable th) {
      System.err.println(th);
    }
}
}
