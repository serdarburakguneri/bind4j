package io.bind4j.http.spi;

/**
 * Media type constants.
 *
 * @since 0.1.0
 */
public final class MediaType {
  private MediaType() {}

  public static final String TEXT_PLAIN = "text/plain";
  public static final String TEXT_PLAIN_UTF8 = "text/plain; charset=utf-8";
  public static final String TEXT_HTML = "text/html";
  public static final String TEXT_HTML_UTF8 = "text/html; charset=utf-8";
  public static final String APPLICATION_JSON = "application/json";
  public static final String APPLICATION_JSON_UTF8 = "application/json; charset=utf-8";
  public static final String APPLICATION_XML = "application/xml";
  public static final String APPLICATION_XML_UTF8 = "application/xml; charset=utf-8";
  public static final String APPLICATION_OCTET_STREAM = "application/octet-stream";
  public static final String MULTIPART_FORM_DATA = "multipart/form-data";
  public static final String APPLICATION_FORM_URLENCODED = "application/x-www-form-urlencoded";
  public static final String APPLICATION_PDF = "application/pdf";
  public static final String IMAGE_JPEG = "image/jpeg";
  public static final String IMAGE_PNG = "image/png";
  public static final String IMAGE_GIF = "image/gif";
}
