package pergunta.raciocinio;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import engine.graphics.Anchor;
import engine.graphics.Rectangle;

public class Boat extends Rectangle {
	private ArrayList<Person> people;
	private Side currentSide;
	private long maxWeight;

	public Boat(Point pos, Anchor anchor, int width, int height, String text, long maxWeight) {
		super(pos, anchor, width, height, text);
		this.setMaxWeight(maxWeight);
		people = new ArrayList<Person>();
		currentSide = Side.Left;
	}

	public ArrayList<Person> getPeople() {
		return people;
	}

	public boolean addPerson(Person person) {
		if (this.getPeople().contains(person)) {
			return false;
		}
		if (person.getCurrentSide() != this.getCurrentSide()) {
			return false;
		}
		if (this.currentWeight() + person.getWeight() <= this.getMaxWeight()) {
			this.people.add(person);
			return true;
		}
		return false;
	}

	public void removePerson(Person person) {
		this.people.remove(person);
	}

	public Side getCurrentSide() {
		return currentSide;
	}

	public void setCurrentSide(Side currentSide) {
		this.currentSide = currentSide;
	}

	public long getMaxWeight() {
		return maxWeight;
	}

	private void setMaxWeight(long maxWeight) {
		this.maxWeight = maxWeight;
	}

	public int currentWeight() {
		int totalWeight = 0;
		for (Person p : this.getPeople()) {
			totalWeight += p.getWeight();
		}
		return totalWeight;
	}

	public void flipSide() {
		Side newSide = Side.Left;
		if (getCurrentSide() == Side.Left) {
			newSide = Side.Right;
		} else if (getCurrentSide() == Side.Right) {
			newSide = Side.Left;
		}
		setCurrentSide(newSide);

		ArrayList<Person> currentPeople = this.getPeople();
		for (Person p : currentPeople) {
			p.flipSide(newSide);
		}
		currentPeople.clear();
	}

	public void move(int dx, int dy) {
		super.move(dx, dy);
		for (Person p : this.getPeople()) {
			p.move(dx, dy);
		}
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
	}
}
