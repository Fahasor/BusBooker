#include <jni.h>
#include <string.h>

extern
JNIEXPORT jobject JNICALL
Java_com_example_data_repository_BusRoutesManagerImpl_getBusRoutesNative(JNIEnv *env, jobject thiz, jstring route) {
    jclass routeDbClass = (*env)->FindClass(env, "com/example/data/repository/RoutesDb");

    jfieldID kotlinMinskMozyrId = (*env)->GetStaticFieldID(env, routeDbClass, "minskMozyr", "Ljava/lang/String;");
    jstring kotlinMinskMozyr = (*env)->GetStaticObjectField(env, routeDbClass, kotlinMinskMozyrId);

    const char* cRoute = (*env)->GetStringUTFChars(env, route, NULL);
    const char* minskMozyr = (*env)->GetStringUTFChars(env, kotlinMinskMozyr, NULL);

    if(strcmp(cRoute, minskMozyr) == 0)
    {
        jfieldID kotlinMinskMozyrRouteListId = (*env)->GetStaticFieldID(
            env, routeDbClass, "minskMozyrRouteList", "Ljava/util/List;");

        jobject kotlinMinskMozyrRouteList =
            (*env)->GetStaticObjectField(env, routeDbClass, kotlinMinskMozyrRouteListId);

        return kotlinMinskMozyrRouteList;
    }
    else
    {
        jfieldID kotlinMozyrMinskRouteListId = (*env)->GetStaticFieldID(
                env, routeDbClass, "mozyrMinskRouteList", "Ljava/util/List;");

        jobject kotlinMozyrMinskRouteList =
                (*env)->GetStaticObjectField(env, routeDbClass, kotlinMozyrMinskRouteListId);

        return kotlinMozyrMinskRouteList;
    }
}