Feature: Tasks API
    A Task should be retrieved by is name and it's id

Scenario: Task WriteTest description by Name
    Given a Task named "WriteTest"
    And the Task "WriteTest" description is "Writing tests is useful and it can be fun!"
    When a Task is requested with name "WriteTest"
    Then the description is "Writing tests is useful and it can be fun!"

Scenario: Task WriteTest description by Id
    Given a Task named "WriteTest"
    And the Task "WriteTest" description is "Writing tests is important so do it!"
    When a Task with name "WriteTest" is requested by is id
    Then the description is "Writing tests is important so do it!"

