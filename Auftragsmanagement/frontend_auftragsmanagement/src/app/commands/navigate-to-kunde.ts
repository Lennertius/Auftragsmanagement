import { Router } from "@angular/router";
import { Command } from "./command";


export class NavigateToKundeCommand implements Command {

constructor(private router: Router) {}

execute(): void {
    this.router.navigate(['/kunde']);
}

}
