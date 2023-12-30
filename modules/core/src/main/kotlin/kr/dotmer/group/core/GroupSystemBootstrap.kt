package kr.dotmer.group.core

import kr.dotmer.group.core.settings.Settings
import kr.hqservice.framework.global.core.component.HQModule

class GroupSystemBootstrap : HQModule {
    override fun onEnable() {
        Settings.reload()
    }
}