# KotlinBukkitPluginTemplate

 * gradle 의 java 버전을 17로 설정해야합니다.
 * api 모듈에는 bukkit 의 코드가 최대한 들어가지 않도록 설계합니다.
 * api 모듈에는 최대한 구현체 없이 추상 객체 설계를 지향합니다.
 * bukkit-api 의 plugin 을 구현하는 ( HQBukkitPlugin 구현체 ) 는 dist 모듈에 작성합니다.
 * plugin 구현체에는 최대한 아무런 로직이 없도록 설계합니다.
 * onEnable ( 기능에 대한 초기 셋업 과정 ) 이 필요한 경우 HQModule 을 구현하여 해당 기능 ( 모듈 ) 마다 bootstrap 과정을 나누어 작성합니다.
