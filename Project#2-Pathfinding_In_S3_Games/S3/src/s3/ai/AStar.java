package s3.ai;


import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import s3.base.S3;
import s3.entities.S3PhysicalEntity;
import s3.util.Node;
import s3.util.Pair;


public class AStar {

	private PriorityQueue<Node> openList = new PriorityQueue<Node>();
	private ArrayList<Node> closedList = new ArrayList<>();
	private Node start;
	private Node goal;
	private S3PhysicalEntity i_entity;
	private S3 the_game;
	private static double[][] moves = {{1.0, 0}, {-1.0, 0}, {0, 1.0}, {0, -1.0}};


	public static int pathDistance(double start_x, double start_y, double goal_x, double goal_y,
			S3PhysicalEntity i_entity, S3 the_game) {
		AStar a = new AStar(start_x,start_y,goal_x,goal_y,i_entity,the_game);
		List<Pair<Double, Double>> path = a.computePath();
		if (path!=null) return path.size();
		return -1;
	}

	public AStar(double start_x, double start_y, double goal_x, double goal_y,
			S3PhysicalEntity i_entity, S3 the_game) {

			this.start = new Node(start_x, start_y);
			this.goal = new Node(goal_x, goal_y);
			this.start.g = 0;
			this.start.h = this.start.heuristic(this.goal);
			this.i_entity = i_entity;
			this.the_game = the_game;
			openList.add(this.start);
	}

	public List<Pair<Double, Double>> computePath() {
			Node lowestF;
			ArrayList<Pair<Double, Double>> output = new ArrayList<>();

			while((lowestF = this.openList.poll()) != null){

				if (lowestF.equals(this.goal)){
					// Node to Lists through backtracking
					while(!lowestF.equals(this.start)){
						output.add(0, new Pair<>(lowestF.x, lowestF.y));
						lowestF = lowestF.parent;
					}
					return output;
				}

				this.closedList.add(lowestF);

				for (double[] move: moves){
					Node tmpNode = new Node(lowestF.x+move[0], lowestF.y+move[1]);
					if (this.isMovePossible(tmpNode)
							&& !closedList.contains(tmpNode)
							&& !openList.contains(tmpNode))
					{
						tmpNode.parent = lowestF;
						tmpNode.g = lowestF.g + 1;
						tmpNode.h = tmpNode.heuristic(this.goal);
						this.openList.add(tmpNode);
					}

				}
			}
            return null;
	}

	// check if move results in valid position on map
	// also checks if resulting position is not accessible such as water, trees, etc.
	public boolean isMovePossible(Node tmp) {
		if (tmp.x >= 0 && tmp.y >=0
				&& tmp.x < this.the_game.getMap().getWidth()
				&& tmp.y < this.the_game.getMap().getHeight())
		{
			S3PhysicalEntity tmpEntity = (S3PhysicalEntity) this.i_entity.clone();
			tmpEntity.setX((int) tmp.x);
			tmpEntity.setY((int) tmp.y);

			return this.the_game.anyLevelCollision(tmpEntity) == null;
		}
		return false;
	}

}
