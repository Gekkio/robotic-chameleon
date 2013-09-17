#define THROW_ILLEGAL_STATE(MSG) { \
  jclass exClass = env->FindClass("java/lang/IllegalStateException"); \
  env->ThrowNew(exClass, MSG); \
}

#define THROW_ILLEGAL_ARGUMENT(MSG) { \
  jclass exClass = env->FindClass("java/lang/IllegalArgumentException"); \
  env->ThrowNew(exClass, MSG); \
}

#define DECLARE_BUFFER(J_BUF, BUF, BUF_TYPE) \
  BUF_TYPE BUF = (BUF_TYPE) env->GetDirectBufferAddress(J_BUF); \
  if (BUF == NULL) { \
    THROW_ILLEGAL_ARGUMENT("ByteBuffer " #BUF " is not a direct buffer"); \
    return; \
  }

#define DECLARE_STRIDE(J_STRIDE, STRIDE) \
  int STRIDE = J_STRIDE; \
  if (STRIDE < 0) { \
    THROW_ILLEGAL_ARGUMENT("Stride " #STRIDE " must be positive"); \
    return; \
  }

#define PLANE(PREFIX, BUF_TYPE, X) \
  DECLARE_BUFFER(j_ ## PREFIX ## X, PREFIX ## X, BUF_TYPE) \
  DECLARE_STRIDE(j_ ## PREFIX ## Stride ## X, PREFIX ## Stride ## X)

#define SRC_PLANE(X) PLANE(src, const uint8*, X)
#define DST_PLANE(X) PLANE(dst, uint8*, X)

#define PLANE_2(PREFIX, BUF_TYPE, X, X1, X2) \
  DECLARE_BUFFER(j_ ## PREFIX ## X, PREFIX ## X, BUF_TYPE); \
  DECLARE_STRIDE(j_ ## PREFIX ## Stride ## X1, PREFIX ## Stride ## X1); \
  DECLARE_STRIDE(j_ ## PREFIX ## Stride ## X2, PREFIX ## Stride ## X2); \
  BUF_TYPE PREFIX ## X1 = PREFIX ## X; \
  BUF_TYPE PREFIX ## X2 = PREFIX ## X1 + (PREFIX ## Stride ## X1 * height);

#define SRC_PLANE_2(X, X1, X2) PLANE_2(src, const uint8*, X, X1, X2)
#define DST_PLANE_2(X, X1, X2) PLANE_2(dst, uint8*, X, X1, X2)

#define PLANE_3(PREFIX, BUF_TYPE, X, X1, X2, X3, SUBY) \
  DECLARE_BUFFER(j_ ## PREFIX ## X, PREFIX ## X, BUF_TYPE); \
  DECLARE_STRIDE(j_ ## PREFIX ## Stride ## X1, PREFIX ## Stride ## X1); \
  DECLARE_STRIDE(j_ ## PREFIX ## Stride ## X2, PREFIX ## Stride ## X2); \
  DECLARE_STRIDE(j_ ## PREFIX ## Stride ## X3, PREFIX ## Stride ## X3); \
  BUF_TYPE PREFIX ## X1 = PREFIX ## X; \
  BUF_TYPE PREFIX ## X2 = PREFIX ## X1 + (PREFIX ## Stride ## X1 * height); \
  BUF_TYPE PREFIX ## X3 = PREFIX ## X2 + (PREFIX ## Stride ## X2 * height / SUBY);

#define SRC_PLANE_3(X, X1, X2, X3, SUBY) PLANE_3(src, const uint8*, X, X1, X2, X3, SUBY)
#define DST_PLANE_3(X, X1, X2, X3, SUBY) PLANE_3(dst, uint8*, X, X1, X2, X3, SUBY)

#define CALL(NAME, ...) \
  int result = NAME(__VA_ARGS__, width, height); \
  if (result != 0) { \
    THROW_ILLEGAL_STATE(#NAME " failed") \
  }

#define CALL_SRC_1(S1) \
  src ## S1, srcStride ## S1

#define CALL_SRC_2(S1, S2) \
  src ## S1, srcStride ## S1, \
  src ## S2, srcStride ## S2

#define CALL_SRC_3(S1, S2, S3) \
  src ## S1, srcStride ## S1, \
  src ## S2, srcStride ## S2, \
  src ## S3, srcStride ## S3

#define CALL_DST_1(D1) \
  dst ## D1, dstStride ## D1

#define CALL_DST_2(D1, D2) \
  dst ## D1, dstStride ## D1, \
  dst ## D2, dstStride ## D2

#define CALL_DST_3(D1, D2, D3) \
  dst ## D1, dstStride ## D1, \
  dst ## D2, dstStride ## D2, \
  dst ## D3, dstStride ## D3

#define CALL_1_1(NAME, S1,         D1)         CALL(NAME, CALL_SRC_1(S1),         CALL_DST_1(D1))
#define CALL_2_1(NAME, S1, S2,     D1)         CALL(NAME, CALL_SRC_2(S1, S2),     CALL_DST_1(D1))
#define CALL_3_1(NAME, S1, S2, S3, D1)         CALL(NAME, CALL_SRC_3(S1, S2, S3), CALL_DST_1(D1))
#define CALL_1_2(NAME, S1,         D1, D2)     CALL(NAME, CALL_SRC_1(S1),         CALL_DST_2(D1, D2))
#define CALL_2_2(NAME, S1, S2,     D1, D2)     CALL(NAME, CALL_SRC_2(S1, S2),     CALL_DST_2(D1, D2))
#define CALL_3_2(NAME, S1, S2, S3, D1, D2)     CALL(NAME, CALL_SRC_3(S1, S2, S3), CALL_DST_2(D1, D2))
#define CALL_1_3(NAME, S1,         D1, D2, D3) CALL(NAME, CALL_SRC_1(S1),         CALL_DST_3(D1, D2, D3))
#define CALL_2_3(NAME, S1, S2,     D1, D2, D3) CALL(NAME, CALL_SRC_2(S1, S2),     CALL_DST_3(D1, D2, D3))
#define CALL_3_3(NAME, S1, S2, S3, D1, D2, D3) CALL(NAME, CALL_SRC_3(S1, S2, S3), CALL_DST_3(D1, D2, D3))

#define PLANES_1_TO_1(NAME, S1, D1) \
  static void JNICALL JVM_ ## NAME(JNIEnv *env, jclass, \
      jobject j_src ## S1, jint j_srcStride ## S1, \
      jobject j_dst ## D1, jint j_dstStride ## D1, \
      jint width, jint height) { \
    SRC_PLANE(S1); \
    DST_PLANE(D1); \
    CALL_1_1(NAME, S1, D1); \
  }

#define PLANES_2_TO_1(NAME, S1, S2, D1) \
  static void JNICALL JVM_ ## NAME(JNIEnv *env, jclass, \
      jobject j_src ## S1, jint j_srcStride ## S1, \
      jobject j_src ## S2, jint j_srcStride ## S2, \
      jobject j_dst ## D1, jint j_dstStride ## D1, \
      jint width, jint height) { \
    SRC_PLANE(S1); \
    SRC_PLANE(S2); \
    DST_PLANE(D1); \
    CALL_2_1(NAME, S1, S2, D1); \
  }

#define PLANES_2P_TO_1(NAME, S, S1, S2, D1) \
  static void JNICALL JVM_ ## NAME ## _S(JNIEnv *env, jclass, \
      jobject j_src ## S, \
      jint j_srcStride ## S1, \
      jint j_srcStride ## S2, \
      jobject j_dst ## D1, \
      jint j_dstStride ## D1, \
      jint width, jint height) { \
    SRC_PLANE_2(S, S1, S2); \
    DST_PLANE(D1); \
    CALL_2_1(NAME, S1, S2, D1); \
  }

#define PLANES_3_TO_1(NAME, S1, S2, S3, D1) \
  static void JNICALL JVM_ ## NAME(JNIEnv *env, jclass, \
      jobject j_src ## S1, jint j_srcStride ## S1, \
      jobject j_src ## S2, jint j_srcStride ## S2, \
      jobject j_src ## S3, jint j_srcStride ## S3, \
      jobject j_dst ## D1, jint j_dstStride ## D1, \
      jint width, jint height) { \
    SRC_PLANE(S1); \
    SRC_PLANE(S2); \
    SRC_PLANE(S3); \
    DST_PLANE(D1); \
    CALL_3_1(NAME, S1, S2, S3, D1); \
  }

#define PLANES_3P_TO_1(NAME, S, S1, S2, S3, SY, D1) \
  static void JNICALL JVM_ ## NAME ## _S(JNIEnv *env, jclass, \
      jobject j_src ## S, \
      jint j_srcStride ## S1, \
      jint j_srcStride ## S2, \
      jint j_srcStride ## S3, \
      jobject j_dst ## D1, \
      jint j_dstStride ## D1, \
      jint width, jint height) { \
    SRC_PLANE_3(S, S1, S2, S3, SY); \
    DST_PLANE(D1); \
    CALL_3_1(NAME, S1, S2, S3, D1); \
  }

#define PLANES_1_TO_2(NAME, S1, D1, D2) \
  static void JNICALL JVM_ ## NAME(JNIEnv *env, jclass, \
      jobject j_src ## S1, jint j_srcStride ## S1, \
      jobject j_dst ## D1, jint j_dstStride ## D1, \
      jobject j_dst ## D2, jint j_dstStride ## D2, \
      jint width, jint height) { \
    SRC_PLANE(S1); \
    DST_PLANE(D1); \
    DST_PLANE(D2); \
    CALL_1_2(NAME, S1, D1, D2); \
  }

#define PLANES_1_TO_2P(NAME, S1, D, D1, D2) \
  static void JNICALL JVM_ ## NAME ## _D(JNIEnv *env, jclass, \
      jobject j_src ## S1, jint j_srcStride ## S1, \
      jobject j_dst ## D, \
      jint j_dstStride ## D1, \
      jint j_dstStride ## D2, \
      jint width, jint height) { \
    SRC_PLANE(S1); \
    DST_PLANE_2(D, D1, D2); \
    CALL_1_2(NAME, S1, D1, D2); \
  }

#define PLANES_2_TO_2(NAME, S1, S2, D1, D2) \
  static void JNICALL JVM_ ## NAME(JNIEnv *env, jclass, \
      jobject j_src ## S1, jint j_srcStride ## S1, \
      jobject j_src ## S2, jint j_srcStride ## S2, \
      jobject j_dst ## D1, jint j_dstStride ## D1, \
      jobject j_dst ## D2, jint j_dstStride ## D2, \
      jint width, jint height) { \
    SRC_PLANE(S1); \
    SRC_PLANE(S2); \
    DST_PLANE(D1); \
    DST_PLANE(D2); \
    CALL_2_2(NAME, S1, S2, D1, D2); \
  }

#define PLANES_2P_TO_2(NAME, S, S1, S2, D1, D2) \
  static void JNICALL JVM_ ## NAME ## _S(JNIEnv *env, jclass, \
      jobject j_src ## S, \
      jint j_srcStride ## S1, \
      jint j_srcStride ## S2, \
      jobject j_dst ## D1, jint j_dstStride ## D1, \
      jobject j_dst ## D2, jint j_dstStride ## D2, \
      jint width, jint height) { \
    SRC_PLANE_2(S, S1, S2); \
    DST_PLANE(D1); \
    DST_PLANE(D2); \
    CALL_2_2(NAME, S1, S2, D1, D2); \
  }

#define PLANES_2_TO_2P(NAME, S1, S2, D, D1, D2) \
  static void JNICALL JVM_ ## NAME ## _D(JNIEnv *env, jclass, \
      jobject j_src ## S1, jint j_srcStride ## S1, \
      jobject j_src ## S2, jint j_srcStride ## S2, \
      jobject j_dst ## D, \
      jint j_dstStride ## D1, \
      jint j_dstStride ## D2, \
      jint width, jint height) { \
    SRC_PLANE(S1); \
    SRC_PLANE(S2); \
    DST_PLANE_2(D, D1, D2); \
    CALL_2_2(NAME, S1, S2, D1, D2); \
  }

#define PLANES_2P_TO_2P(NAME, S, S1, S2, D, D1, D2) \
  static void JNICALL JVM_ ## NAME ## _SD(JNIEnv *env, jclass, \
      jobject j_src ## S, \
      jint j_srcStride ## S1, \
      jint j_srcStride ## S2, \
      jobject j_dst ## D, \
      jint j_dstStride ## D1, \
      jint j_dstStride ## D2, \
      jint width, jint height) { \
    SRC_PLANE_2(S, S1, S2); \
    DST_PLANE_2(D, D1, D2); \
    CALL_2_2(NAME, S1, S2, D1, D2); \
  }

#define PLANES_3_TO_2(NAME, S1, S2, S3, D1, D2) \
  static void JNICALL JVM_ ## NAME(JNIEnv *env, jclass, \
      jobject j_src ## S1, jint j_srcStride ## S1, \
      jobject j_src ## S2, jint j_srcStride ## S2, \
      jobject j_src ## S3, jint j_srcStride ## S3, \
      jobject j_dst ## D1, jint j_dstStride ## D1, \
      jobject j_dst ## D2, jint j_dstStride ## D2, \
      jint width, jint height) { \
    SRC_PLANE(S1); \
    SRC_PLANE(S2); \
    SRC_PLANE(S3); \
    DST_PLANE(D1); \
    DST_PLANE(D2); \
    CALL_3_2(NAME, S1, S2, S3, D1, D2); \
  }

#define PLANES_3_TO_2P(NAME, S1, S2, S3, D, D1, D2) \
  static void JNICALL JVM_ ## NAME ## _D(JNIEnv *env, jclass, \
      jobject j_src ## S1, jint j_srcStride ## S1, \
      jobject j_src ## S2, jint j_srcStride ## S2, \
      jobject j_src ## S3, jint j_srcStride ## S3, \
      jobject j_dst ## D, \
      jint j_dstStride ## D1, \
      jint j_dstStride ## D2, \
      jint width, jint height) { \
    SRC_PLANE(S1); \
    SRC_PLANE(S2); \
    SRC_PLANE(S3); \
    DST_PLANE_2(D, D1, D2); \
    CALL_3_2(NAME, S1, S2, S3, D1, D2); \
  }

#define PLANES_3P_TO_2(NAME, S, S1, S2, S3, SY, D1, D2) \
  static void JNICALL JVM_ ## NAME ## _S(JNIEnv *env, jclass, \
      jobject j_src ## S, \
      jint j_srcStride ## S1, \
      jint j_srcStride ## S2, \
      jint j_srcStride ## S3, \
      jobject j_dst ## D1, jint j_dstStride ## D1, \
      jobject j_dst ## D2, jint j_dstStride ## D2, \
      jint width, jint height) { \
    SRC_PLANE_3(S, S1, S2, S3, SY); \
    DST_PLANE(D1); \
    DST_PLANE(D2); \
    CALL_3_2(NAME, S1, S2, S3, D1, D2); \
  }

#define PLANES_3P_TO_2P(NAME, S, S1, S2, S3, SY, D, D1, D2) \
  static void JNICALL JVM_ ## NAME ## _SD(JNIEnv *env, jclass, \
      jobject j_src ## S, \
      jint j_srcStride ## S1, \
      jint j_srcStride ## S2, \
      jint j_srcStride ## S3, \
      jobject j_dst ## D, \
      jint j_dstStride ## D1, \
      jint j_dstStride ## D2, \
      jint width, jint height) { \
    SRC_PLANE_3(S, S1, S2, S3, SY); \
    DST_PLANE_2(D, D1, D2); \
    CALL_3_2(NAME, S1, S2, S3, D1, D2); \
  }

#define PLANES_1_TO_3(NAME, S1, D1, D2, D3) \
  static void JNICALL JVM_ ## NAME(JNIEnv *env, jclass, \
      jobject j_src ## S1, jint j_srcStride ## S1, \
      jobject j_dst ## D1, jint j_dstStride ## D1, \
      jobject j_dst ## D2, jint j_dstStride ## D2, \
      jobject j_dst ## D3, jint j_dstStride ## D3, \
      jint width, jint height) { \
    SRC_PLANE(S1); \
    DST_PLANE(D1); \
    DST_PLANE(D2); \
    DST_PLANE(D3); \
    CALL_1_3(NAME, S1, D1, D2, D3); \
  }

#define PLANES_1_TO_3P(NAME, S1, D, D1, D2, D3, DY) \
  static void JNICALL JVM_ ## NAME ## _D(JNIEnv *env, jclass, \
      jobject j_src ## S1, jint j_srcStride ## S1, \
      jobject j_dst ## D, \
      jint j_dstStride ## D1, \
      jint j_dstStride ## D2, \
      jint j_dstStride ## D3, \
      jint width, jint height) { \
    SRC_PLANE(S1); \
    DST_PLANE_3(D, D1, D2, D3, DY); \
    CALL_1_3(NAME, S1, D1, D2, D3); \
  }

#define PLANES_2_TO_3(NAME, S1, S2, D1, D2, D3) \
  static void JNICALL JVM_ ## NAME(JNIEnv *env, jclass, \
      jobject j_src ## S1, jint j_srcStride ## S1, \
      jobject j_src ## S2, jint j_srcStride ## S2, \
      jobject j_dst ## D1, jint j_dstStride ## D1, \
      jobject j_dst ## D2, jint j_dstStride ## D2, \
      jobject j_dst ## D3, jint j_dstStride ## D3, \
      jint width, jint height) { \
    SRC_PLANE(S1); \
    SRC_PLANE(S2); \
    DST_PLANE(D1); \
    DST_PLANE(D2); \
    DST_PLANE(D3); \
    CALL_2_3(NAME, S1, S2, D1, D2, D3); \
  }

#define PLANES_2P_TO_3(NAME, S, S1, S2, D1, D2, D3) \
  static void JNICALL JVM_ ## NAME ## _S(JNIEnv *env, jclass, \
      jobject j_src ## S, \
      jint j_srcStride ## S1, \
      jint j_srcStride ## S2, \
      jobject j_dst ## D1, jint j_dstStride ## D1, \
      jobject j_dst ## D2, jint j_dstStride ## D2, \
      jobject j_dst ## D3, jint j_dstStride ## D3, \
      jint width, jint height) { \
    SRC_PLANE_2(S, S1, S2); \
    DST_PLANE(D1); \
    DST_PLANE(D2); \
    DST_PLANE(D3); \
    CALL_2_3(NAME, S1, S2, D1, D2, D3); \
  }

#define PLANES_2_TO_3P(NAME, S1, S2, D, D1, D2, D3, DY) \
  static void JNICALL JVM_ ## NAME ## _D(JNIEnv *env, jclass, \
      jobject j_src ## S1, jint j_srcStride ## S1, \
      jobject j_src ## S2, jint j_srcStride ## S2, \
      jobject j_dst ## D, \
      jint j_dstStride ## D1, \
      jint j_dstStride ## D2, \
      jint j_dstStride ## D3, \
      jint width, jint height) { \
    SRC_PLANE(S1); \
    SRC_PLANE(S2); \
    DST_PLANE_3(D, D1, D2, D3, DY); \
    CALL_2_3(NAME, S1, S2, D1, D2, D3); \
  }

#define PLANES_2P_TO_3P(NAME, S, S1, S2, D, D1, D2, D3, DY) \
  static void JNICALL JVM_ ## NAME ## _SD(JNIEnv *env, jclass, \
      jobject j_src ## S, \
      jint j_srcStride ## S1, \
      jint j_srcStride ## S2, \
      jobject j_dst ## D, \
      jint j_dstStride ## D1, \
      jint j_dstStride ## D2, \
      jint j_dstStride ## D3, \
      jint width, jint height) { \
    SRC_PLANE_2(S, S1, S2); \
    DST_PLANE_3(D, D1, D2, D3, DY); \
    CALL_2_3(NAME, S1, S2, D1, D2, D3); \
  }

#define PLANES_3_TO_3(NAME, S1, S2, S3, D1, D2, D3) \
  static void JNICALL JVM_ ## NAME(JNIEnv *env, jclass, \
      jobject j_src ## S1, jint j_srcStride ## S1, \
      jobject j_src ## S2, jint j_srcStride ## S2, \
      jobject j_src ## S3, jint j_srcStride ## S3, \
      jobject j_dst ## D1, jint j_dstStride ## D1, \
      jobject j_dst ## D2, jint j_dstStride ## D2, \
      jobject j_dst ## D3, jint j_dstStride ## D3, \
      jint width, jint height) { \
    SRC_PLANE(S1); \
    SRC_PLANE(S2); \
    SRC_PLANE(S3); \
    DST_PLANE(D1); \
    DST_PLANE(D2); \
    DST_PLANE(D3); \
    CALL_3_3(NAME, S1, S2, S3, D1, D2, D3); \
  }

#define PLANES_3_TO_3P(NAME, S1, S2, S3, D, D1, D2, D3, DY) \
  static void JNICALL JVM_ ## NAME ## _D(JNIEnv *env, jclass, \
      jobject j_src ## S1, jint j_srcStride ## S1, \
      jobject j_src ## S2, jint j_srcStride ## S2, \
      jobject j_src ## S3, jint j_srcStride ## S3, \
      jobject j_dst ## D, \
      jint j_dstStride ## D1, \
      jint j_dstStride ## D2, \
      jint j_dstStride ## D3, \
      jint width, jint height) { \
    SRC_PLANE(S1); \
    SRC_PLANE(S2); \
    SRC_PLANE(S3); \
    DST_PLANE_3(D, D1, D2, D3, DY); \
    CALL_3_3(NAME, S1, S2, S3, D1, D2, D3); \
  }

#define PLANES_3P_TO_3(NAME, S, S1, S2, S3, SY, D1, D2, D3) \
  static void JNICALL JVM_ ## NAME ## _S(JNIEnv *env, jclass, \
      jobject j_src ## S, \
      jint j_srcStride ## S1, \
      jint j_srcStride ## S2, \
      jint j_srcStride ## S3, \
      jobject j_dst ## D1, jint j_dstStride ## D1, \
      jobject j_dst ## D2, jint j_dstStride ## D2, \
      jobject j_dst ## D3, jint j_dstStride ## D3, \
      jint width, jint height) { \
    SRC_PLANE_3(S, S1, S2, S3, SY); \
    DST_PLANE(D1); \
    DST_PLANE(D2); \
    DST_PLANE(D3); \
    CALL_3_3(NAME, S1, S2, S3, D1, D2, D3); \
  }

#define PLANES_3P_TO_3P(NAME, S, S1, S2, S3, SY, D, D1, D2, D3, DY) \
  static void JNICALL JVM_ ## NAME ## _SD(JNIEnv *env, jclass, \
      jobject j_src ## S, \
      jint j_srcStride ## S1, \
      jint j_srcStride ## S2, \
      jint j_srcStride ## S3, \
      jobject j_dst ## D, \
      jint j_dstStride ## D1, \
      jint j_dstStride ## D2, \
      jint j_dstStride ## D3, \
      jint width, jint height) { \
    SRC_PLANE_3(S, S1, S2, S3, SY); \
    DST_PLANE_3(D, D1, D2, D3, DY); \
    CALL_3_3(NAME, S1, S2, S3, D1, D2, D3); \
  }

#define JNI_BUF         "Ljava/nio/ByteBuffer;"
#define JNI_PLANE       JNI_BUF "I"
#define JNI_CONVERT(NAME, PLANES) { #NAME, "(" PLANES "II)V", (void*) JVM_ ## NAME }
