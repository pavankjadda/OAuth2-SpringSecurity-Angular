import {CommonModule} from "@angular/common";
import {NgModule} from "@angular/core";
import {ReactiveFormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";
import {NgxSpinnerModule} from "ngx-spinner";
import {AddressTypeComponent} from "./address-type/address-type.component";
import {AddressComponent} from "./address/address.component";
import {ApiRoutingModule} from "./api-routing.module";
import {CategoryDeleteComponent} from "./category/category-delete/category-delete.component";
import {CategoryEditComponent} from "./category/category-edit/category-edit.component";
import {CategoryHomeComponent} from "./category/category-home/category-home.component";
import {CategoryListComponent} from "./category/category-list/category-list.component";
import {CategoryNewComponent} from "./category/category-new/category-new.component";
import {CategoryViewComponent} from "./category/category-view/category-view.component";
import {CategoryComponent} from "./category/category.component";

import {CityComponent} from "./city/city.component";
import {CountryComponent} from "./country/country.component";
import {RegionComponent} from "./region/region.component";
import {StateComponent} from "./state/state.component";


@NgModule({
  imports: [
    RouterModule,
    CommonModule,
    ReactiveFormsModule,
    NgxSpinnerModule,
    ApiRoutingModule
  ],

  declarations: [
    CityComponent,
    CountryComponent,
    RegionComponent,
    StateComponent,
    AddressComponent,
    AddressTypeComponent,
    CategoryComponent,
    CategoryListComponent,
    CategoryViewComponent,
    CategoryNewComponent,
    CategoryHomeComponent,
    CategoryEditComponent,
    CategoryDeleteComponent
  ],
  exports: [
    RouterModule
  ]
})

export class ApiModule {

}


