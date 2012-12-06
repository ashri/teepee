#!/usr/bin/env ruby

require 'fileutils'
require 'tmpdir'

static_dir = "/Users/ashley/Development/three-headed-monkey/teepee/src/main/webapp"
taskpaper_file = ARGV[0]
unless taskpaper_file
    puts "Usage: teepee-go.rb <taskpaper-file>"
    exit
end

# Create temporary directory
temp_dir = Dir.mktmpdir
#puts "Temporary directory: #{temp_dir}"

# Copy static files there
status = `cp -R #{static_dir}/* #{temp_dir}`
unless status
    puts "Failed to copy static files"
    exit
end

# Call out to generate from Taskpaper files
status = `java -jar teepee.jar "#{taskpaper_file}" #{temp_dir}`
unless status
    puts "Failed to generate files"
    exit
end

# Send files up to server
status = `scp -r #{temp_dir}/* tearsofaunicorn.org:/srv/apps/teepee/public_html`
unless status
    puts "Failed to copy files to server"
    exit
end

# Clean up
`rm -r #{temp_dir}`
