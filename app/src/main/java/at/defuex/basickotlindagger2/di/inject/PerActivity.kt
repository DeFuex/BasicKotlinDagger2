package at.defuex.basickotlindagger2.di.inject

import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

/**
 * Created by timoobereder on 03.12.17.
 */

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerActivity