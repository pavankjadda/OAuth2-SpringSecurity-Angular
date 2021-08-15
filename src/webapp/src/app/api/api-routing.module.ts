import {NgModule} from "@angular/core";
import {RouterModule} from "@angular/router";

import {categoryManagementRoute} from "./category/category.route";


@NgModule(
  {
  imports: [
    RouterModule.forChild(categoryManagementRoute),
  ],
  exports: [
    RouterModule
  ]
} )
export class ApiRoutingModule
{
}
