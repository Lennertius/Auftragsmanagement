import { Router } from "@angular/router";
import { Command } from "./command";


export class NavigateToHomeCommand implements Command {

constructor(private router: Router) {}

execute(): void {
    this.router.navigate(['/landingpage']);
}

}

