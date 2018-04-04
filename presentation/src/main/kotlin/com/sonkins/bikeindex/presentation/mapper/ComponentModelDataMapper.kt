package com.sonkins.bikeindex.presentation.mapper

import com.sonkins.bikeindex.domain.model.Component
import com.sonkins.bikeindex.presentation.model.ComponentModel
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 04 April 2018.
 */
class ComponentModelDataMapper @Inject constructor() {

    fun transform(component: Component) = ComponentModel(
            component.id,
            component.description,
            component.serialNumber,
            component.componentType,
            component.componentGroup,
            component.rear,
            component.front,
            component.manufacturerName,
            component.modelName,
            component.year
    )

    fun transform(components: List<Component>): List<ComponentModel> {
        val componentModels = ArrayList<ComponentModel>()

        for (component in components) {
            componentModels.add(transform(component))
        }

        return componentModels
    }

}