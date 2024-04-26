import { Profile } from "./profile";
import { Recommendation } from "./recommendation";

export class User
{
    id: number;
    username: string;
    email: string;
    password: string;
    histories: History[];
    recommendations: Recommendation[];
    profile?: Profile;
}