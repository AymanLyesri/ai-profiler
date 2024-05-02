import { Coordinate } from "./coordinate";
import { Interaction } from "./interaction";
import { Purchase } from "./purchase";
import { Recommendation } from "./recommendation";
import { Topic } from "./topic";

export class History
{
    id: number;
    topic: Topic;
    interactions: Interaction[];
    purchases: Purchase[];
    coordinates: Coordinate[];
    recommendation: Recommendation;
}