import {CommonModule} from "@angular/common";
import {NgModule} from "@angular/core";
import {ReactiveFormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";
import {NgxSpinnerModule} from "ngx-spinner";
import {ApiRoutingModule} from "./api-routing.module";

import {CategoryDeleteComponent} from "./category/category-delete/category-delete.component";
import {CategoryEditComponent} from "./category/category-edit/category-edit.component";
import {CategoryHomeComponent} from "./category/category-home/category-home.component";
import {CategoryListComponent} from "./category/category-list/category-list.component";
import {CategoryNewComponent} from "./category/category-new/category-new.component";
import {CategoryViewComponent} from "./category/category-view/category-view.component";
import {CategoryComponent} from "./category/category.component";


@NgModule({
  imports: [
    RouterModule,
    CommonModule,
    ReactiveFormsModule,
    NgxSpinnerModule,
    ApiRoutingModule
  ],

  declarations: [
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


