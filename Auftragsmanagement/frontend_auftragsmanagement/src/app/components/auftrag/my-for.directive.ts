import {
  Directive,
  Input,
  TemplateRef,
  ViewContainerRef
} from '@angular/core';

@Directive({
  selector: '[myFor]' // Nutzung: *myFor="let item of items"
})
export class MyForDirective<T> {
  @Input()
  set myForOf(items: T[]) {
    this.viewContainer.clear();
    for (let i = 0; i < items.length; i++) {
      this.viewContainer.createEmbeddedView(this.templateRef, {
        $implicit: items[i],
        index: i
      });
    }
  }

  constructor(
    private templateRef: TemplateRef<any>,
    private viewContainer: ViewContainerRef
  ) {}
}
