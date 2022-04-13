
// 프로젝트의 논리적인 경로를 물리적인 경로와 다르게 설정하기 위함
fun includeProject(name: String, projectPath: String? = null) {
    include(name)
    projectPath?.run {
        project(name).projectDir = File(this)
    }
}

rootProject.name = "kotlin-gradle-multi-module-boilerplate"
includeProject(":api", "modules/api")
includeProject(":application", "modules/application")
includeProject(":domain", "modules/domain")
includeProject(":infrastructure", "modules/infrastructure")
